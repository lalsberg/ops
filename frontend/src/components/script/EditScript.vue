<template>
	<div class="wrapper">
		<label>Titulo</label>
		<input v-model="script.title" class="my-input"></input>

		<label>Script</label>
		<textarea v-model="script.content" class="my-input"></textarea>

		<label>Solução</label>
		<textarea v-model="script.solution" class="my-input"></textarea>

		<my-button @buttonClicked='save()' class="create-button">
			<span>Save</span>
		</my-button>

	</div>
</template>


<script>
	import TButton from '../shared/TButton.vue';

	export default {

	    components: {
	        'my-button': TButton
	    },

		data () {
			return {
				script: {
					id: 0,
					title: '',
					content: '',
					solution: ''
				}
			}
		},

		methods: {
			save: function() {
				this.$http.put('http://localhost:8080/script', this.script)
					.then(res => this.$router.push("/"), err => console.log(err));
			}
		},

		created() {
			this.$http.get('http://localhost:8080/script/' + this.$route.params.id)
				.then(res => res.json())
				.then(script => this.script = script, err => console.log(err));
		}

	}
</script>


<style>
	input { 
		line-height: 2em;
	}

	.wrapper {
		width: 90%;
		margin: 0 auto;
		font-family: helvetica_neue_lt_std45_light,sans-serif;
	}

	@media (min-width: 1024px) {
		.wrapper {
			width: 40%;
		}
	}

	.my-input {
		display: block;
		margin: 10px 0px;
	}

	input {
		width: 100%;
	}

	textarea {
		width: 100%;
		height: 500px;
	}

	.create-button {
		float: right;
	}
</style>
