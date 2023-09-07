<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
</style>
</head>
<body>
	<div class="container">
	 <h1 class="text-center">{{music_title}}</h1>
	 <div class="row">
			<div class="text-center">
				<b-button variant="outline-danger" @click="musicListData(1)">Top100</b-button>
				<b-button variant="outline-success" @click="musicListData(2)">가요</b-button>
				<b-button variant="outline-warning" @click="musicListData(3)">POP</b-button>
				<b-button variant="outline-info" @click="musicListData(4)">OST</b-button>
				<b-button variant="outline-primary" @click="musicListData(5)">트롯</b-button>
				<b-button variant="outline-default" @click="musicListData(6)">JAZZ</b-button>
				<b-button variant="outline-danger" @click="musicListData(7)">CLASSIC</b-button>
			</div>
		</div>
		<div style="height: 20px;"></div>
		<div class="row">
		<music v-bind:musicdata="music_list"></music>
			<b-modal ref="my-modal" hide-footer :title="music_detail.title" v-if="isShow" id="modal-lg" size="lg">
				<div class="text-center">
					<iframe :src="'http://youtude.com/embed/'+music_detail.key" style="width: 600px;height: 500px"></iframe>
				</div>
			</b-modal>
		</div>
	</div>
	<script>
	//component => Vue 통신
	let eventBus=new Vue();
		Vue.component('music',{ //태그명 지정
			props:['musicdata'],
			template:'<table class="table">' +
		    '<tr class="success">' +
		    '<th class="text-center">순위</th>' +
		    '<th class="text-center"></th>' +
		    '<th class="text-center">곡명</th>' +
		    '<th class="text-center">가수명</th>' +
		    '<th class="text-center"></th>' +
		    '</tr>' +
		    '<tbody>' +
		    '<tr v-for="vo in musicdata">' +
		    '<td class="text-center">{{ vo.mno }}</td>' +
		    '<td class="text-center"><img :src="vo.poster" style="width:30px;height:30px"></td>' + // 여기서 '>' 부분을 닫는 괄호로 수정해야 합니다.
		    '<td>{{ vo.title }}</td>' +
		    '<td>{{ vo.singer }}</td>' +
		    '<td class="text-center">' +
		    '<b-button id="show-btn" variant="outline-success" @click="showMovie(vo.mno,true)">동영상</b-button>' +
		    '</td>' +
		    '</tr>' +
		    '</tbody>' +
		    '</table>',
		    methods:{
		    	showMovie:function(value,bool){
		    		eventBus.$emit('showMovieEvent',value,bool)
		    	}
		    }
		}) 
		
		new Vue({
			el:'.container',
			data:{
				music_list:[],
				music_detail:{},
				isShow:false,
				cno:1,
				music_title:''
			}, 
			mounted:function(){
				this.musicListData(this.cno);
			},
			update:function(){
				let _this=this
				eventBus.$on('showMovieEvent',function(value,bool){
					_this.isShow=bool;
					axios.get('http://localhost/web/music/detail_vue.do',{
						params:{
							mno:value
						}
					}).then(res=>{
						console.log(res.data)
						_this.music_detail=res.data;
						_this.musicShow();
						
					})
					
				})
			
				
			},
			methods:{
				musicListData:function(cno){
					axios.get('http://localhost/web/music/list_vue.do',{
						params:{
							cno:cno
						}
					}).then(res=>{
						console.log(res.data)
						this.music_list=res.data;
						this.music_title=res.data[0].m_title;
						
					})
				},
				musicShow:function(){
					this.$refs['my-modal'].show();
				}
				
			}
			
		})
		
	</script>	
</body>
</html>