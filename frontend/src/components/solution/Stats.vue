<template>
	<div class="stats-chart">
		<!-- <chart v-if="loaded" :chartdata="chartdata" :options="options"/> -->
		<chart v-if="loaded" :chartdata="chartdata"/>

	</div>
</template>

<script>
	import Chart from './Chart.vue'

	export default {
		name: 'Stats',

		components: { 
			'chart': Chart
		},

		data() {
			return {
				loaded: false,
				chartdata: null
			}
		},

		created() {
			this.loaded = false;
			var chartdata = {};
			chartdata.datasets = [];
			this.$http.get('http://localhost:8080/solutions/stats')
			// .then(res => console.log(res.body))
			.then(res => res.json())
			.then(res => {
				chartdata.labels = Object.keys(res.totalByDate)

				var dataset = {};
				dataset.label = Object.keys(res)[0];
				dataset.backgroundColor = '#f87979';
				dataset.data = Object.values(res.totalByDate);
				chartdata.datasets.push(dataset);

				this.chartdata = chartdata;
				this.loaded=true;
				console.log(this.chartdata);
			})
			;
		}
	}
</script>

<style>
	.stats-chart {
		width: 30%;
		height: 30%;
	}
</style>