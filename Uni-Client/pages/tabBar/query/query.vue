<template>
	<view class="">
		<view class="uni-center">
			<image class="image" style="width: 100%;height: 360upx;" mode="" src="../../../static/title_bg.jpg" />
		</view>
		<view class="uni-list">
			<view class="uni-list-cell" hover-class="uni-list-cell-hover" v-for="(value,key) in listData" :key="key" >
				<view class="uni-media-list">
					<view class="uni-media-list-body">
						<view class="uni-media-list-text-top">{{value.userid}}</view>
						<view class="uni-media-list-text-bottom">
							<text>{{value.user}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	var dateUtils = require('../../../common/util.js').dateUtils;
	var limit = 10,
		page = 1;
	var data = {
		/* "cmsNews.moduleId": moduleId, */
		"page": page,
		"limit": limit
	}
	export default {
		data() {
			return {
				listData: [],
				reload: false
			}
		},
		onLoad() {
			this.reload = true;
			this.getList();
		},
		onPullDownRefresh() {
			this.reload = true;
			this.getList();
		},
		onReachBottom() {
			this.reload = true;
			this.getList();
		},
		methods: {
			getList() {
				data.page = page++;
				var url = "http://localhost:8086/mobile/action/userActionFind";
				uni.request({
					url: url,
					data: data,
					success: (data) => {
						var rsp = data.data;
							let list = this.setTime(rsp);
							this.listData = this.reload ? list : this.listData.concat(list);
							this.reload = false;
					},
					fail: (rsp, code) => {
						console.log('fail' + JSON.stringify(rsp));
					}
				})
			},
	
			setTime: function(items) {
				var newItems = [];
				if(!items){
					return;
				}
				items.forEach((e) => {
					newItems.push({
						userid: e.userid,
						user:e.user
					});
				});
				return newItems;
			}
		},
	}
</script>

<style>
	page {
		height: auto;
		min-height: 100%;
	}

	.uni-card {
		box-shadow: none;
	}

	.uni-list:after {
		height: 0;
	}

	.uni-list:before {
		height: 0;
	}

	.uni-media-list-logo {
		width: 180upx;
		height: 140upx;
	}

	.uni-media-list-body {
		height: auto;
		justify-content: space-around;
	}

	.uni-media-list-text-top {
		height: 74upx;
		font-size: 28upx;
		overflow: hidden;
	}

	.uni-media-list-text-bottom {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
</style>
