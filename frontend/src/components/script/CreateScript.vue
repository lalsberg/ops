<template>
	<div class="wrapper">
		<label>Titulo</label>
		<input v-model="script.title" class="my-input"></input>

		<label>Script</label>
		<textarea v-model="script.content" class="my-input"></textarea>

		<label>Solução</label>
		<textarea v-model="script.solution" class="my-input"></textarea>

		<my-button @buttonClicked='register()' class="create-button">
			<span>Register</span>
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
					title: '',
					content: '',
					solution: ''
				}
			}
		},

		methods: {
			register: function() {
				this.$http.post('http://localhost:8080/scripts', this.script)
					.then(function(response) {
						console.log(response);
						this.$router.push("/");
					}
					, err => {this.sending = false; console.log(err)});
			}
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
