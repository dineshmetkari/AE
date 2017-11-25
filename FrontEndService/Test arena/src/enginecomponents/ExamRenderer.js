import React, { Component } from 'react';
import MCQTemplate from './MCQTemplate';
import NavPanel from './NavPanel';
import Paper from 'material-ui/Paper';
import SockJS from "sockjs-client";
import Stomp from "@stomp/stompjs";
import Counter from './Counter';
var socket = null;
var stompClient =null;

const layoutStyle={
	height:'75vh',
	width:'80vw',
	border:'2px solid',
	margin:'auto',
	marginTop:'0px',
}
const questionStyle={
	height:'50vh',
	width:'70vw',
	border:'2px solid',
	margin:'auto',
	marginTop:'20px',
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
			userid:'inthu@gmail.com',
			qNumber:1,
			currentQNumber:1,
			qData:null,
			NumOfQuestions:null,
			selectedAnswer:null,
			isDisconnected:'opened',
			msg:'Exam',
			flag: this.props.flag
		};

	}

	componentWillMount(){
		this.connect();
	}

	onClickNav(NewQues){
		const {qNumber} = this.state;
		this.setState({currentQNumber:qNumber})
		this.setState({
			qNumber:NewQues
		})


		  //this.connect();
		setTimeout(this.sendQuestion, 10);
	}

	sendQuestion() {
		var t = this;
    stompClient.send("/app/questions/"+t.props.userName, {}, JSON.stringify({'examStatus':t.state.isDisconnected,'userid':t.state.userid,'id':t.state.currentQNumber,'question': 'Question1','nextQuestion':t.state.qNumber,'selectedAnswer':t.state.selectedAnswer,'options':t.state.options}));
}
		sendQuestionAfterSubmit() {
		var t = this;
    stompClient.send("/app/questions/"+t.props.userName, {}, JSON.stringify({'examStatus':'close','userid':t.state.userid,'id':t.state.currentQNumber,'question': 'Question1','nextQuestion':t.state.qNumber,'selectedAnswer':t.state.selectedAnswer,'options':t.state.options}));
}
	connect() {
    var that=this;
    socket = new SockJS("http://172.23.238.225:8081/engine");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/question/'+that.state.userid, function (greeting) {
          console.log(JSON.parse(greeting.body).question);
          that.setState({NumOfQuestions: JSON.parse(greeting.body).noOfQuestions});
          that.setState({msg: JSON.parse(greeting.body).msg});
          that.setState({
          	qData: greeting.body,

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

    	 getTimerFlag(flag){
    this.props.sendTimerFlag(flag);
  }

	render(){
		const {isDisconnected, msg } = this.state;
		if(msg === 'Exam Started'){
		if(this.state.flag === 'Timeup' && isDisconnected === 'opened'){
						this.disconnect();
			return(
				<div style={{display:'flex', alignSelf:'center',height:'80vh', alignItems:'center'}}>
				<div style={{display:'flex', flexDirection:'column',alignItems:'center', height:'30vh', width:'65vw', border:'solid 1px green', backgroundColor:'#CEFFCE', margin:'auto', borderRadius:'10px'}}>
						<h3 style={{color:'green', padding:'20px'}}>Submitted Successfully!</h3>
						<p style={{color:'green'}}>For Results, Please Visit Student Operations & click on View Result</p>
				</div>
				</div>
				);

		}
		else{
		return(
			<div style={{}}>
				<div style={{display:'flex', flexDirection:'column', alignItems:'center'}}>
				<Counter hh={1} mm={30} ss={0} sendTimerFlag={this.getTimerFlag.bind(this)}/>
				<Paper style={layoutStyle} zDepth={3}>
				<Paper style={questionStyle} zDepth={1}>
				<div>
					<MCQTemplate jsonData={this.state.qData} setSelectedAnswer={this.setAnswer.bind(this)} />
				</div>
				</Paper>


				<div style={{width:'70vw', margin:'auto'}}>
					<NavPanel closeConnection={this.disconnect.bind(this)}
						questionCount={this.state.NumOfQuestions}
						questionClick={this.onClickNav.bind(this)}
						/>
				</div>
				</Paper>
				</div>
			</div>

		);
	}
	}else{
		return(
			<div>
			<h2>Logged in as {this.props.userName}</h2>
			<div style={{display:'flex', alignSelf:'center',height:'80vh', alignItems:'center'}}>
			<div style={{display:'flex',alignItems:'center',justifyContent:'center',backgroundColor:'#FFCC80',color:'#E65100', width:'50vw', height:'20vh', margin:'auto', border:'2px solid #E65100', borderRadius:'5px'}}>
			<h2>Exam Not yet Started</h2>
			</div>
			</div>
			</div>
		);
	}
}
}

export default ExamRenderer;
