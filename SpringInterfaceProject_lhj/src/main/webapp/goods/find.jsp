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
	width: 80%;
}
</style>
</head>
<body>
	<div class="container-fluid" >
		<header class="heading">
          	 <input type=text ref="gname" size=30 class="input-sm" v-model="gname"> 
			<input type=button class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
          </header>
		<div class="row">
          	<div class="col-md-4" v-for="vo in goods_find_list">
				    <div class="thumbnail">
				      <a href="/w3images/lights.jpg">
				        <img :src="vo.goods_image" alt="Lights" style="width:100%">
				        <div class="caption">
				          <p style="font-size: 10px">{{vo.goods_name }}</p>
				        </div>
				      </a>
				    </div>
 			   </div>
        </div>
        <div style="height: 10px"></div>
		<div class="row">
			<div class="text-center">
					<input type="button" value="이전" class="btn btn-sm btn-danger" v-on:click="prev()">
						{{curpage}} page/ {{totalpage}} pages
 					<input type="button" value="다음" class="btn btn-sm btn-danger" v-on:click="next()">
			</div>
		</div>
    </div>
     <script>
     	new Vue({
     		el:'.container-fluid',
     		data:{
     			curpage:1,
     			totalpage:0,
     			goods_find_list:[],
     			gname:'우'
     		},
     		mounted:function(){
     		 	this.goodsFind();
     		},
     		methods:{
     			goodsFind:function(){
     				axios.get("http://localhost/web/goods/find_vue.do",{
     					params:{
     						page:this.curpage,
     						gname:this.gname
     					}
     				}).then(response=>{
     					this.curpage=response.data[0].curpage;
     					this.totalpage=response.data[0].totalpage;
     					this.goods_find_list=response.data;
     				})
     			},
     			prev:function(){
					this.curpage=this.curpage>1?this.curpage-1:this.curpage;
					this.goodsFind();
				},
				next:function(){
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
					this.goodsFind();
				},
				findData:function(){
					this.curpage=1;
					this.goodsFind();
				}
     			
     		}
     	})
     </script>     
</body>
</html>