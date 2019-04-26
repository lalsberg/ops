<template>
	<div class="wrapper">
		<input type="search" class="filter" placeholder="Filter solutions" v-on:input="filter = $event.target.value">

		<div class="solution-panel" v-for="solution in filteredSolutions">
			<the-solution :solution="solution"/>
		</div>
	</div>
</template>


<script>
	import Solution from '../../components/solution/Solution.vue';

	export default {
		name: 'home',

		components: {
			'the-solution': Solution
		},

		data() {
			return {
				solutions: [],
				filter: ''
			}
		},

		computed: {
			filteredSolutions() {
				if (!this.filter) {
					return this.solutions;
				}
				let exp = new RegExp(this.filter.trim(), 'i');
				return this.solutions.filter(solution => exp.test(solution.title));
			}
		},

		created() {
			this.$http.get('http://localhost:8080/solutions')
				.then(res => res.json())
				.then(solutions => this.solutions = solutions, err => console.log(err));
		}
	}
</script>


<style>
	h1 {
		font-weight: normal;
	}

	.wrapper {
		width: 90%;
		margin: 0 auto
	}

	@media (min-width: 1024px) {
		.wrapper {
			width: 40%;
		}
	}

	.filter {
		display: block;
		width: 100%;
		-webkit-appearance: textfield;
		line-height: 1em;
		padding: 1em 1em;
		margin: 0 auto
	}
</style>
