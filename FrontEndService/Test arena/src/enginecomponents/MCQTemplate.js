import React, { Component } from 'react';


class MCQTemplate extends Component{
	constructor(props){
		super(props);
		this.state={
			answered:null,
			isChecked:'',
		};
	this.handleChange=this.handleChange.bind(this);
	}

	handleChange(event){

		this.props.setSelectedAnswer(event.target.value);
		this.setState({
			answered:event.target.value
		});
	}

	
	render(){
		const {answered, isChecked} = this.state;
		if(this.props.jsonData != null){
			const Options = JSON.parse(this.props.jsonData).options;
			const Question = JSON.parse(this.props.jsonData).question;
			const QuestionId = JSON.parse(this.props.jsonData).questionId;
			
			var renderOptions = null;
			renderOptions = Options.map(Option => {
				return(
					<div>
							<span style={{marginRight:'10px'}}>
								<input type='radio'
								key={Option}
								id={Option}
								value={Option}
					
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