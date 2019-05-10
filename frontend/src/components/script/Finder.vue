<template>
	<div class="wrapper">
		<div class="search-line">
			<input type="search" class="filter" placeholder="Enter match ID" v-on:input="filter = $event.target.value">
			<tbutton class="btn-search" @buttonClicked="search()">
				<i class="material-icons">search</i>
			</tbutton>
		</div>

		<div class="script-panel" v-for="script in scripts">
			<the-script :script="script"/>
		</div>
	</div>
</template>


<script>
	import TButton from '../../components/shared/TButton.vue';
	import Script from '../../components/script/Script.vue';

	export default {
		name: 'finder',

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

		methods: {
			search: function() {
				this.$http.get('http://localhost:8080/scripts/discover', {params:  {filter: this.filter}})
						.then(res => res.json())
						.then(scripts => this.scripts = scripts, err => console.log(err));
    		}
    	}
		
	}
</script>


<style>
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

	.btn-search {
		display: inline-block;
		width: 3em;
	}
</style>
