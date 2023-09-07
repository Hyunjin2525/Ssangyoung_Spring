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
			<div class="col-md-3" v-for="vo in recipe_list">
			    <div class="thumbnail">
			      <a :href="#"> <!--  Vue로 번호 보내는 법 -->
			        <img :src="vo.poster" :title="vo.title" style="width:100%">
			        <div class="caption">
			        </div>
			      </a>
			    </div>
 			</div>
		</div>
		<div style="height:10px;"></div>
		<div class="row">
			<ul class="pagination">
			  <li><a href="#" @click="prev()">&lt;</a></li>
			  <li v-for="i in range(startpage,endpage)" :class="i==curpage?'active':''"><a href="#" @click="selectPage(i)">{{i}}</a></li>
			  <li><a href="#" @click="next()">&gt;</a></li>
			</ul>
		</div>
	</div>
	<script>
		new Vue({
			el:'.container-fluid',
			data:{
				curpage:1,
				totalpage:0,
				startpage:0,
				endpage:0,
				recipe_list:[]
			},
			mounted:function(){
				this.send();
			},
			methods:{
				send:function(){
					axios.get("http://localhost/web/recipe/list_vue.do",{
						params: {
							page:this.curpage
						}
					}).then(response=>{
						this.recipe_list=response.data;
						this.curpage=response.data[0].curpage;
						this.startpage=response.data[0].startpage;
						this.endpage=response.data[0].endpage;
						this.totalpage=response.data[0].totalpage;
							
					})
				},range:function(start,end){
					let arr=[];
					let length=end-start;
					for(let i=0;i<=length;i++)
					{
						arr[i]=start;
						start++;
					}
					return arr;
				},
				prev:function(){
					this.curpage=this.startpage-1;
					this.send();
				},
				next:function(){
					this.curpage=this.endpage+1;
					this.send();
				},
				selectPage:function(page){
					this.curpage=page;
					this.send();
				}
			}
		})
	</script>

</body>
</html>