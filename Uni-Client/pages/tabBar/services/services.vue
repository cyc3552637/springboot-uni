<template>
	<view>
		<view class="uni-center" style="background-color:#61A1F1 ;">
			<image class="image" style="width: 100%;height: 320upx;" src="../../../static/service_bg.png"></image>
			<view class="title1">用户信息登记</view>
		</view>
		<view class="content">
		<view class="input-group">
			<view class="input-row border">
				<text class="title">用户名：</text>
				<input type="text" name="userid"  v-model="userid"  placeholder="请输入userid">
			</view>
			<view class="input-row">
				<text class="title">密&#12288码：</text>
				<input class="code" name="user"  type="text" v-model="user"  placeholder="请输入user">
				
			</view>
		</view>
		<view class="btn-row">
			<button type="primary" class="primary" @tap="bindRegister">注册</button>
		</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userid:'',
				user:''	
				}
			},
		methods: {
			bindRegister(){
				if ((this.userid).length < 1) {
					uni.showToast({
						icon: 'none',
						title: '请输入正确的userid!'
					});
					return;
				}
				if ((this.user).length < 1) {
					uni.showToast({
						icon: 'none',
						title: '请输入正确的user!'
					});
					return;
				}
				this.goRegister();
				
			},
			goRegister(){
				uni.request({
					url: "http://localhost:8086/mobile/action/userActionAdd",
					data: {
						userid: this.userid,
						user:this.user
					},
					success: (data) => {
						var rsp = data.data;
						console.log(data)
						console.log(JSON.stringify(rsp))
						if (JSON.stringify(rsp) == 'true') {
							// plus.nativeUI.alert(rsp.user);
						    this.userid='';
							this.user='';
							uni.showToast({
								icon: 'none',
								title: '注册成功'
							});
						
						}
						else{
							uni.showToast({
								icon: 'none',
								title: '注册失败'
							});
						}
						
					},
					fail: (rsp, code) => {
						console.log('fail' + JSON.stringify(rsp));
						uni.showToast({
							icon: 'none',
							title: '注册失败'
						});
					}
				}) 
			},
			loading() {
				this.num -= 1;
				this.text = '获取中(' + this.num + ')';
			},
			inputChange(e){
			                this.value = e.value
			            }
		
	}

	}
</script>

<style>
	

	.title1 {
		max-height: 84upx;
		overflow: hidden;
		position: absolute;
		right: 30upx;
		top: 100upx;
		font-size: 35upx;
		font-weight: 400;
		line-height: 35upx;
		color: white;
		z-index: 11;
	}

	.title2 {
		overflow: hidden;
		position: absolute;
		right: 30upx;
		top: 180upx;
		font-size: 40upx;
		color: #61A1F1;
		z-index: 11;
	}

	.uni-grid-9 {
		background-color: #FFFFFF;
	}
	.uni-common-mt{
		margin-top: 0;
	}

	.uni-grid-9-image {
		height: 80upx;
		width: 80upx;
	}

	.uni-grid-9-text {
		margin-top: 30upx;
	}
	.input-group {
		background-color: #ffffff;
		margin-top: 30px;
		position: relative;
	}
	
	.input-group::before {
		position: relative;
		right: 0;
		top: 0;
		left: 0;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}
	
	.input-group::after {
		position: relative;
		right: 0;
		bottom: 0;
		left: 0;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}
	
	.input-row {
		display: flex;
		flex-direction: row;
		position: relative;
	}
	
	.input-row .title {
		width: 20%;
		height: 50px;
		min-height: 50px;
		padding-left: 15px;
		line-height: 50px;
	}
	
	.input-row input {
		width: 80%;
		height: 50px;
		min-height: 50px;
		padding-right: 30px;
		line-height: 50px;
	}
	
	.input-row .code {
		width: 50%;
		height: 50px;
		min-height: 50px;
		line-height: 50px;
	}
	
	.input-row .btn_code {
		width: 30%;
		height: 50px;
		font-size: 13px;
		min-height: 50px;
		line-height: 50px;
		padding: 0;
	}
	
	.input-row.border::after {
		position: absolute;
		right: 0;
		bottom: 0;
		left: 15px;
		height: 1px;
		content: '';
		-webkit-transform: scaleY(.5);
		transform: scaleY(.5);
		background-color: #c8c7cc;
	}
	.content {
		display: flex;
		flex: 1;
		flex-direction: column;
		background-color: #efeff4;
		padding: 20px;
	}
</style>
