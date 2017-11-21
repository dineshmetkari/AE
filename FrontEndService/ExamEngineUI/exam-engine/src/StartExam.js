import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import ExamPlayer from './ExamPlayer/ExamPlayer';
import SockJS from "sockjs-client"
import Stomp from "@stomp/stompjs"
import Appbar from './Appbar';
import Footer from './Footer';
import './StartExam.css';
var socket = null;
var stompClient =null;


class StartExam extends Component{
	constructor(props) {
	    super(props);
	    this.state = {
	      showExamPlayer:false
	    };
	  this.handleClick=this.handleClick.bind(this);
  	}

  	handleClick(){
  		//this.connect();
  		this.setState({
  			showExamPlayer:'true'
  		});
  	}
	render(){
		if(!this.state.showExamPlayer){
		return(
			<div className="mainContainer">
				<Appbar />

				<div style={{display:'flex', flexDirection:'column', marginTop:'100px', alignItems:'center'}}>
					<div style={{display:'flex', flexDirection:'column',alignItems:'left', height:'250', width:'750', border:'solid 1px black', color:'#787878', backgroundColor:'#F5F5F5', margin:'auto', marginBottom:'70px' }}>
						<h2 style={{alignSelf:'center'}}>Instructions</h2>
						<div style={{marginLeft:'50px', fontSize:'14px'}}>
						<ul>
						<li>Exam Duration : 90 Minutes</li>
						<li>Contains 10 to 30 questions.</li>
						<li>Contains MCQ and True/False.</li>
						<li>Refer Timer at top right to see duration left.</li>
						<li>Click question Number to navigate to particular question.</li>
						<li>Press Submit only if you wish to finish exam.</li>
						</ul>
						</div>
					</div>
					<RaisedButton label="Start Exam" labelStyle={{fontWeight:'bold', fontSize:'16px', lineHeight:'40px'}} style={{height:'40px', width:'160px'}} onClick={this.handleClick} backgroundColor="#46d246" labelColor="white" />
				</div>

				<div className="footer">
				<Footer />
				</div>
			</div>
			);
	}
		if(this.state.showExamPlayer){
		return(
			<div>
				<Appbar />
				<ExamPlayer msg={this.state.showExamPlayer} />
				<div className="footer">
				<Footer />
				</div>
			</div>
			);
	}

	}
}

export default StartExam;