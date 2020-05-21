<template>
	<view class="content">
		<view class="input-group">
			<view class="input-row border">
				<text class="title">用户名：</text>
				<input type="text" v-model="userid" placeholder="请输入userid">
			</view>
			<view class="input-row">
				<text class="title">密&#12288码：</text>
				<input class="code" type="text" v-model="user" placeholder="请输入user">
				
			</view>
		</view>
		<view class="btn-row">
			<button type="primary" class="primary" @tap="bindLogin">登录</button>
		</view>
	</view>
</template>

<script>
	var utils = require('../../../../common/util.js');
	export default {
		methods: {
			bindLogin(){
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
				this.goLogin();
				
			},
			goLogin(){
				uni.request({
					url: "http://localhost:8086/mobile/action/userActionLogin",
					data: {
						userid: this.userid,
						user:this.user
					},
					success: (data) => {
						var rsp = data.data[0];
						console.log(data)
						console.log(JSON.stringify(rsp))
						if (typeof(rsp)!="undefined") {
							// plus.nativeUI.alert(rsp.user);
							uni.showToast({
								icon: 'none',
								title: '登陆成功'
							});
							setTimeout(function(){
							 uni.reLaunch({
                         url: '../../query/query',
                         });
						 },1000
						 )
						}
						else{
							uni.showToast({
								icon: 'none',
								title: '登陆失败'
							});
						}
						
					},
					fail: (rsp, code) => {
						console.log('fail' + JSON.stringify(rsp));
						uni.showToast({
							icon: 'none',
							title: '登陆失败'
						});
					}
				}) 
			},
			loading() {
				this.num -= 1;
				this.text = '获取中(' + this.num + ')';
			}
	}
	}
</script>

<style>
	.content {
		display: flex;
		flex: 1;
		flex-direction: column;
		background-color: #efeff4;
		padding: 20px;
	}

	.input-group {
		background-color: #ffffff;
		margin-top: 40px;
		position: relative;
	}

	.input-group::before {
		position: absolute;
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
		position: absolute;
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

	.btn-row {
		margin-top: 50px;
		padding: 20px;
	}

	button.primary {
		background-color: #0faeff;
	}
</style>
