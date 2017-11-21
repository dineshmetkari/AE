import React, { Component } from 'react';
import Template1 from './Template1';
class MCQTemplate extends Component{
	constructor(props){
		super(props);
		this.state={
			answered:null
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
		if(this.props.jsonData != null){
			const Options = JSON.parse(this.props.jsonData).options;
			const Question = JSON.parse(this.props.jsonData).question;
			const renderOptions = Options.map(Option => {
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