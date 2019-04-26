<template>
	<div class="solution">

		<div class="solution-head">
			<span> {{ solution.title }}</span>
		</div>

		<div class="solution-description">
			<p><span><pre>{{ solution.description }}</pre></span></p>
		</div>

		<div class="frequency">
			<p class="mg-font-bold total-value-desc">Frequência:</p>
			<p class="mg-font-bold mg-font-size-xl real-value"> {{ solution.frequency }} </p>
			<tbutton @buttonClicked="incrementFrequency(solution)" class="btn-increment-frequency">
				<i class="material-icons">exposure_plus_1</i>
			</tbutton>
		</div>

	</div>
</template>

<script>
import TButton from '../shared/TButton.vue'

export default {
	name: 'Solution',
	

    props: {
        solution: {
            required: true
        }
    },

    components: {
    	'tbutton': TButton
    },

    methods: {
    	incrementFrequency: function(solution) {
    		this.$http.post('http://localhost:8080/solution/' + solution.id + '/frequency')
					.then(function(response) {
						console.log(response);
						this.solution.frequency++;
					}
					, err => {this.sending = false; console.log(err)});
    	}
    }
}
</script>

<style>
	.frequency {
	    padding: 0.9em;
	    border-top: 1px solid silver;
	}

	p {
	    -webkit-margin-before: 0;
	    -webkit-margin-after: 0;
	    -webkit-margin-start: 0;
	    -webkit-margin-end: 0;
	}

	.mg-font-size-xl {
	    font-size: 1.3rem;
	}

	pre {
		overflow-x: auto;
		white-space: pre-wrap;
		white-space: -moz-pre-wrap;
		white-space: -pre-wrap;
		white-space: -o-pre-wrap;
		word-wrap: break-word;
	}

	.solution-head {
		color: rgb(51, 51, 51);
		background-color: rgb(245, 245, 245);
	    border-color: rgb(221, 221, 221);
	    border-top-left-radius: 6px;
	    border-top-right-radius: 6px;
	    padding: 1em 1em;
	    border-bottom: 1px solid silver;
	}

	.solution {
		box-shadow: 0 3px 1px -2px rgba(0,0,0,.2), 0 2px 2px 0 rgba(0,0,0,.14), 0 1px 5px 0 rgba(0,0,0,.12);
	    font-family: helvetica_neue_lt_std45_light,sans-serif;
	    color: #555;
	}

	.solution {
		border: 1px solid silver;
    	border-radius: .4rem;
    	margin: 10px 0;
    	margin-left: auto;
		margin-right: auto;
		margin-top: 1em;
	}

	.total-value-desc {
		display: inline-block;
	}

	.real-value {
		display: inline-block;
	}

	.solution-description {
		padding: 0 1.4rem;	
	}

	.solution-description span, .frequency {
		font-family: Roboto,Noto Sans,-apple-system,BlinkMacSystemFont,sans-serif;
	}

	.mg-font-bold {
	    font-weight: 400;
	}

	.btn-increment-frequency {
		float: right;
	}

</style>
