<template>
	<div class="wrapper">
		<div class="search-line">
			<input type="search" class="filter" placeholder="Filter solutions" v-on:input="filter = $event.target.value">
			<tbutton class="btn-add-solution" to="/createSolution">
				<i class="material-icons">add</i>
			</tbutton>
		</div>
		<div class="solution-panel" v-for="solution in filteredSolutions">
			<the-solution :solution="solution"/>
		</div>
	</div>
</template>


<script>
	import Solution from '../../components/solution/Solution.vue';
	import TButton from '../../components/shared/TButton.vue';


	export default {
		name: 'home',

		components: {
			'the-solution': Solution,
			'tbutton': TButton
		},

		data() {
			return {
				solutions: [],
				filter: ''
			}
		},

		computed: {
			filteredSolutions() {
				var activeSolutions = this.solutions.filter(solution => {return !solution.archived });
				console.log(activeSolutions);
				if (!this.filter) {
					return activeSolutions;
				}
				let exp = new RegExp(this.filter.trim(), 'i');
				return activeSolutions.filter(solution => exp.test(solution.title));
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

	.search-line {
	    display: flex;
	    justify-content: space-between;
		flex-wrap: nowrap;
	}
	.filter {
		display: inline-block;
		width: calc(100% - 5em);
		-webkit-appearance: textfield;
		line-height: 1em;
		padding: 1em 1em;
		margin: 0 auto
	}

	.btn-add-solution {
		display: inline-block;
		width: 3em;
	}
</style>
