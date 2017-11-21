import React, { Component } from 'react';
import ExamRenderer from './ExamRenderer/ExamRenderer';
import Paper from 'material-ui/Paper';

const divStyle={
	height:'400px',
	width:'800px',
	border:'2px solid',
	margin:'auto',
}

class ExamLayout extends Component{
	constructor(props){
		super(props);
	}
	render(){
		return(
			<Paper style={divStyle} zDepth={3}>
			<ExamRenderer flag={this.props.flag} />
			</Paper>
	);
	}
}

export default ExamLayout;
