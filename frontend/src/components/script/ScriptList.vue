<template>
	<div class="wrapper">
		<div class="search-line">
			<input type="search" class="filter" placeholder="Filter scripts" v-on:input="filter = $event.target.value">
			<tbutton class="btn-add-script" to="/createScript">
				<i class="material-icons">add</i>
			</tbutton>
		</div>

		<div class="script-panel" v-for="script in filteredScripts">
			<the-script :script="script"/>
		</div>
	</div>
</template>


<script>
	import Script from '../../components/script/Script.vue';
	import TButton from '../../components/shared/TButton.vue';


	export default {
		name: 'home',

		components: {
			'the-script': Script,
			'tbutton': TButton
		},

		data() {
			return {
				scripts: [],
				filter: ''
			}
		},

		computed: {
			filteredScripts() {
				var activeScripts = this.scripts.filter(script => {return !script.archived });
				console.log(activeScripts);
				if (!this.filter) {
					return activeScripts;
				}
				let exp = new RegExp(this.filter.trim(), 'i');
				return activeScripts.filter(script => exp.test(script.title));
			}
		},

		created() {
			this.$http.get('http://localhost:8080/ops-scripts')
				.then(res => res.json())
				.then(scripts => this.scripts = scripts, err => console.log(err));
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

	.btn-add-script {
		display: inline-block;
		width: 3em;
	}
</style>
