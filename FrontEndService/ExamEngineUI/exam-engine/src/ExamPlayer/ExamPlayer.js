import React, { Component } from 'react';
import ExamTimer from './ExamTimer/ExamTimer';
import ExamLayout from './ExamLayout/ExamLayout';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';


class ExamPlayer extends Component{
	constructor(props){
		super(props);
		this.state={
			TimerFlag: null,
		}
	}
	setTimeUp(flag){
		this.setState({TimerFlag : flag});
	}

	render(){
		const { TimerFlag } = this.state;
		return(
			<MuiThemeProvider>
			<div>
				<ExamTimer sendTimerFlag={this.setTimeUp.bind(this)} />
				<ExamLayout flag={this.state.TimerFlag} />
			</div>
			</MuiThemeProvider>
		);
	}
}

export default ExamPlayer;
