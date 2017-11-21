import React, { Component } from 'react';
import MCQTemplate from './MCQTemplate/MCQTemplate';
import NavPanel from './NavPanel/NavPanel';
import SubmitPanel from './SubmitPanel/SubmitPanel';
import Paper from 'material-ui/Paper';
import SockJS from "sockjs-client"
import Stomp from "@stomp/stompjs"
var socket = null;
var stompClient =null;

const divStyle={
	height:'240px',
	width:'700px',
	border:'2px solid',
	margin:'auto',
	marginTop:'50px',
}
class ExamRenderer extends Component{
	constructor(props){
		super(props);
		 this.connect=this.connect.bind(this)
		 this.sendQuestion=this.sendQuestion.bind(this)
		 this.sendQuestionAfterSubmit=this.sendQuestionAfterSubmit.bind(this)
		  this.disconnect=this.disconnect.bind(this) 
		  this.setIsDisconnected=this.setIsDisconnected.bind(this)  
		this.state={
			userid:'user2',
			qNumber:1,
			qData:null,
			NumOfQuestions:null,
			selectedAnswer:null,
			isDisconnected:'opened',
			flag: this.props.flag
		};

	}

	componentWillMount(){
		this.connect();
	}

	onClickNav(NewQues){
		this.setState({
			qNumber:NewQues
		})
        
        
		  //this.connect();
		setTimeout(this.sendQuestion, 2000);   
	}
	
	sendQuestion() {
		var t = this;
    stompClient.send("/app/questions/"+t.state.userid, {}, JSON.stringify({'examStatus':t.state.isDisconnected,'userid':t.state.userid,'id':t.state.qNumber,'question': 'Question1','nextQuestion':t.state.qNumber,'selectedAnswer':t.state.selectedAnswer,'options':t.state.options}));
}
		sendQuestionAfterSubmit() {
		var t = this;
    stompClient.send("/app/questions/"+t.state.userid, {}, JSON.stringify({'examStatus':'close','userid':t.state.userid,'id':t.state.qNumber,'question': 'Question1','nextQuestion':t.state.qNumber,'selectedAnswer':t.state.selectedAnswer,'options':t.state.options}));
}
	connect() {
    var that=this;
    socket = new SockJS("http://172.23.238.225:8081/engine");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/question/'+that.state.userid, function (greeting) {
          console.log(JSON.parse(greeting.body).question);
          that.setState({NumOfQuestions: JSON.parse(greeting.body).noOfQuestions})
          that.setState({
          	qData: greeting.body
          	         });
                        });
              });
 setTimeout(this.sendQuestion,2000);
}

disconnect(event) {
	this.sendQuestionAfterSubmit();
	this.setIsDisconnected();
	
    if (stompClient !== null) {
    	setTimeout(stompClient.disconnect(), 2000);
        console.log("Disconnected");
    }
}

setAnswer(selectedAnswer){
	this.setState({
		selectedAnswer: selectedAnswer
	});
}


  setIsDisconnected(){
  	this.setState({flag : 'Timeup' });
  }

	render(){
		const {qNumber,	qData, isDisconnected, flag } = this.state;


		if(this.state.flag=='Timeup' && isDisconnected== 'opened'){
			return(
				<div style={{display:'flex', flexDirection:'column',alignItems:'center', height:'200px', width:'600px', border:'solid 1px green', backgroundColor:'#CEFFCE', margin:'auto', marginTop:'60px', borderRadius:'10px'}}>
						<br />
						<h3 style={{color:'green'}}>Submitted Successfully!</h3>
						<p style={{color:'green'}}>For Results, Please Visit Student Operations & click on View Result</p>
				</div>
				);

				this.disconnect();
		}
		else{
		return(
			<div>
				<Paper style={divStyle} zDepth={1}>
				<div>
					<h1>{this.state.flag} {this.state.isDisconnected}</h1>
					<MCQTemplate jsonData={this.state.qData} setSelectedAnswer={this.setAnswer.bind(this)} />
				</div>
				</Paper>
				
				<div>
					<NavPanel closeConnection={this.disconnect.bind(this)}
						questionCount={this.state.NumOfQuestions}
						questionClick={this.onClickNav.bind(this)} 
						/>
				</div>
				
			</div>

		);
	}

	}
}

export default ExamRenderer;