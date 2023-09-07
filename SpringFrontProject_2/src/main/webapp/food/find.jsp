<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.contianer-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<input type=text ref="fd" size=30 class="input-sm" v-model="fd"> <!--  설정한 변수 값을 넣어준다 = v-model -->
			<input type=button class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			      <a href="/w3images/lights.jpg">
			        <img :src="vo.poster" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p style="font-size: 10px">{{vo.name}}&nbsp;<span style="color: orange;">{{vo.score}}</span></p>
			        </div>
			      </a>
			    </div>
 			</div>
		</div>
		<div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
				  <li v-if="startpage>1"><a href="#" v-on:click="prev()">&lt;</a></li>
				  <li v-for="i in range(startpage,endpage)" :class="i==curpage?'active':''"><a href="#" v-on:click="selectPage(i)">{{i}}</a></li>
				  <li v-if="endpage<totalpage"><a href="#" v-on:click="next()">&gt;</a></li>
				</ul>
			</div>
		</div>
	</div>
	<script>
	new Vue({
		el:".container-fluid",
		//react = state
		// React:라이브러리 (완제품) VueJS: 프레임워크 (레고)
		//멤버변수 => 화면이동전까지 저장
		data:{
			fd:'마포',
			food_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0,
			
		},
		//시작과 동시에 데이터를 읽어서 출력
		//Callback = 시스템에 의해 자동으로 호출되는 함수 => 생명주기
		//window.onload => $(function(){}) => componentDidMount => useEffect
		mounted:function(){
			//스프링 서버를 연결해서 => 필요한 데이터를 전송, 결과값을 받는다
			//List => []
			//VO => {}
			this.send(); // 계속해서 여러번 사용될 코드는 함수화 시키기
			
		},
		//멤버함수
		methods:{
			send:function(){
				axios.get("http://localhost/web/food/find_vue.do",{
					params:{
						page:this.curpage,
						fd:this.fd
					}
				}).then(response=>{
					console.log(response.data);
					this.food_list=response.data
					this.curpage=response.data[0].curpage;
					this.totalpage=response.data[0].totalpage;
					this.startpage=response.data[0].startpage;
					this.endpage=response.data[0].endpage;
					
				})
			},
			findData:function(){
				this.curpage=1;
				this.send();
			},
			range:function(start,end){
				let arr=[];
				let length=end-start;
				for(let i=0; i<=length;i++)
				{
					arr[i]=start;
					start++;
				}
				return arr;
				
			},
			selectPage:function(page){
				this.curpage=page;
				this.send();
			},
			prev:function(){
				this.curpage=this.startpage-1;
				this.send();
			},
			next:function(){
				this.curpage=this.endpage+1;
				this.send();
			}
		}
		
	})
	</script>
</body>
</html>