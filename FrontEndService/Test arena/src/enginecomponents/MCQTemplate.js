import React, { Component } from 'react';

class MCQTemplate extends Component{
	constructor(props){
		super(props);
		this.state={
			answered:null,
			isChecked:'',
			useranswer: '',
			flag:'1'
		};
	this.handleChange=this.handleChange.bind(this);

	}

	handleChange(event){
		this.props.setSelectedAnswer(event.target.value);
		this.setState({
			answered:event.target.value,
			useranswer:event.target.value
		});

<<<<<<< HEAD
		}
    shouldComponentUpdate(NextProps,nextState){
    	this.setState({useranswer: ""})
    	return true;
    }
=======
>>>>>>> 8bec38f348020193130c7f0735560c3461339f29

	render(){
		const {answered, isChecked,useranswer} = this.state;
		if(this.props.jsonData != null){
			const Options = JSON.parse(this.props.jsonData).options;
			const Question = JSON.parse(this.props.jsonData).question;
			const QuestionId = JSON.parse(this.props.jsonData).questionId;
			const userAnswer=JSON.parse(this.props.jsonData).userAnswer;
<<<<<<< HEAD
			
			var renderOptions = null;

			if(useranswer==""){
			if(userAnswer=="")
			{
				console.log("UserAnswer is null");
				
=======

			var renderOptions = null;
			if(userAnswer==""){
>>>>>>> 8bec38f348020193130c7f0735560c3461339f29
			renderOptions = Options.map(Option => {
				return(
					<div>
							<span style={{marginRight:'10px'}}>
								<input type='radio'
								key={Option}
								id={Option}
								value={Option}
<<<<<<< HEAD
=======

>>>>>>> 8bec38f348020193130c7f0735560c3461339f29
								name={Question}
								onChange={this.handleChange}/>
							 </span>
							<label>
								{Option}
							</label>
							<br/>
					</div>

					)
				});
			}
			else{

				console.log("UserAnswer is retrieved");
				renderOptions = Options.map(Option => {
				return(
					<div>
							<span style={{marginRight:'10px'}}>
								<input type='radio'
								key={Option}
								id={Option}
								value={Option}
								name={Question}
								checked={userAnswer===Option}
								onChange={this.handleChange}/>
							 </span>
							<label>
								{Option}
							</label>
							<br/>
					</div>

					)
				});
			}
           }
			else{
				console.log("Again UserAnswer is changed");
				renderOptions = Options.map(Option => {
				return(
					<div>
							<span style={{marginRight:'10px'}}>
								<input type='radio'
								key={Option}
								id={Option}
								value={Option}
								name={Question}
								checked={useranswer===Option}
								onChange={this.handleChange}/>
							 </span>
							<label>
								{Option}
							</label>
							<br/>
					</div>

					)
				});
<<<<<<< HEAD
			}
=======
     }
		 else{
			 renderOptions = Options.map(Option => {
 				return(
 					<div>
 							<span style={{marginRight:'10px'}}>
 								<input type='radio'
 								key={Option}
 								id={Option}
 								value={Option}
 								checked={userAnswer===Option}
 								name={Question}
 								onChange={this.handleChange}/>
 							 </span>
 							<label>
 								{Option}
 							</label>
 							<br/>
 					</div>
 					)
 				});
		 }
>>>>>>> 8bec38f348020193130c7f0735560c3461339f29

		return(
			<div style={{margin:'20px'}}>
				<div>
				<p>{QuestionId}: {Question}</p>
				</div>
				<div>
				{renderOptions}
				</div>
			</div>
			)
			}else{
			return(<div></div>);
		}
	}
}

export default MCQTemplate;
