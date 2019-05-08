<template>
	<div class="wrapper">
		<!-- <chart v-if="loaded" :chartdata="chartdata" :options="options"/> -->
		<div class="select-date">
			<div>
				<label>De</label>
				<datepicker format="dd/MM/yyyy" class="datepicker"></datepicker>
			</div>

			<div>
				<label>Até</label>
				<datepicker format="dd/MM/yyyy" class="datepicker"></datepicker>
			</div>

			<tbutton>Filtrar</tbutton>
		</div>

		<chart v-if="loaded" :chartdata="chartdata" class="chart"/>

	</div>
</template>

<script>
	import Chart from './Chart.vue'
	import Datepicker from 'vuejs-datepicker';
	import TButton from '../shared/TButton.vue';

	export default {
		name: 'Stats',

		components: { 
			'chart': Chart,
			Datepicker,
			'tbutton': TButton
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
	.datepicker {
		display: inline-block;
	}
	.select-date {
		display: flex;
		justify-content: space-between;
	}
	.wrapper {
		width: 30%;
		height: 30%;
		margin: 0 auto;
	}

	.chart {
		margin-top: 3em;
	}
</style>