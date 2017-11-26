import React, { Component } from 'react';
import Template1 from './Template1';
class MCQTemplate extends Component{
	constructor(props){
		super(props);
		this.state={
			answered:null,
			selectedAnswer:null
		};
	this.handleChange=this.handleChange.bind(this);
	this.setChecked=this.setChecked.bind(this);
	}

	handleChange(event){

		this.props.setSelectedAnswer(event.target.value);
		this.setState({
			answered:event.target.value
		});
	}

	setChecked(x){
		if(x){
		this.setState({isChecked : 'checked' });
		}
		if(!x){
		this.setState({isChecked : '' });
		}
	}

	
	render(){
		if(this.props.jsonData != null){
			const Options = JSON.parse(this.props.jsonData).options;
			const Question = JSON.parse(this.props.jsonData).question;
			const selectedAnswer= JSON.parse(this.props.jsonData).selectedAnswer;
			const renderOptions = Options.map(Option => {
				if(selectedAnswer == Option){
					this.setChecked(true);
				}else{
					this.setChecked(false);
				}

				return(
					<div>
							<span style={{marginRight:'10px'}}>
								<input type='radio'
								key={Option}
								id={Option}
								value={Option}
								name={Question}
								checked={isChecked}
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
			<div style={{height:'90%', width:'90%', margin:'auto'}}>
				<div>
				<h3>{Question}</h3>
				</div>
				<div>
				{renderOptions}
				</div>
				<div>
				{this.state.answered}
				</div>
			</div>
			)
			}else{
			return(<div></div>);
		}
	}
}

export default MCQTemplate;