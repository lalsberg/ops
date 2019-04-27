<template>
	<div class="wrapper">
		<label>Titulo</label>
		<input v-model="solution.title" class="my-input"></input>

		<label>Descricao</label>
		<textarea v-model="solution.description" class="my-input"></textarea>

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
				solution: {
					id: 0,
					title: '',
					description: ''
				}
			}
		},

		methods: {
			save: function() {
				this.$http.put('http://localhost:8080/solution', this.solution)
					.then(res => this.$router.push("/"), err => console.log(err));
			}
		},

		created() {
			this.$http.get('http://localhost:8080/solution/' + this.$route.params.id)
				.then(res => res.json())
				.then(solution => this.solution = solution, err => console.log(err));
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
