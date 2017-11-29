import React from 'react';
import request from 'superagent';
import TFQuestion from './tFQuestion';
import PreviewMcq from './previewMcq';
import McqQuestion from './mcqQuestion';
import Dialog from 'material-ui/Dialog';
import Drawer from 'material-ui/Drawer';
import AppBar from 'material-ui/AppBar';
import Snackbar from 'material-ui/Snackbar';
import FlatButton from 'material-ui/FlatButton';
import PreviewTrueFalse from './previewTrueFalse';
import RaisedButton from 'material-ui/RaisedButton';
const style = {
  drawer:{
    width: '100%'
  },
  dialog:{
    width: '20%'
  },
  margin: 12,
};
export default class QuestionLayout extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
                  typeValue:'default',
                  levelValue:'',
                  domainValue:'',
                  complexityValue:'',
                  topicValue:'',
                  mcq: false,
                  tf: false,
                  preview: false,
                  status: false,
                  value: '',
                  domain: '',
                  complexity: '',
                  type: '',
                  topic: '',
                  level: '',
                  question: '',
                  answer: '',
                  optionA: '',
                  optionB: '',
                  optionC: '',
                  optionD: '',
                  marks: '',
                  openAlert: false,
                  openSnackBar: false
              }
    this.openPreview = this.openPreview.bind(this);
    this.closePreview =  this.closePreview.bind(this);
    this.getQuestion = this.getQuestion.bind(this);
    this.getOptionA = this.getOptionA.bind(this);
    this.getOptionB = this.getOptionB.bind(this);
    this.getOptionC = this.getOptionC.bind(this);
    this.getOptionD = this.getOptionD.bind(this);
    this.getAnswer = this.getAnswer.bind(this);
    this.save = this.save.bind(this);
  }
  render() {
    const actions = [
      <FlatButton
        label="Ok"
        primary={true}
        onClick={this.handleClose}
      />,
    ];
    return (
      <div>
        <Drawer width={style.drawer.width} openSecondary={true} open={this.state.mcq} >
          <AppBar title="Mcq Question" />
          < McqQuestion onSubmit = {this.getQuestion} getAnswer = {this.getAnswer}
                        optionA = {this.getOptionA} optionB = {this.getOptionB}
                        optionC = {this.getOptionC} optionD = {this.getOptionD}
          />
            <RaisedButton label="cancel" default={true} style={style} onClick = {this.props.setDefault }/>
            <RaisedButton label="preview" secondary={true} style={style} onClick={this.openPreview}/>
            <RaisedButton label="save" primary={true} style={style} onClick = {this.save} />
            <PreviewMcq open = {this.state.preview} setDefault = {this.closePreview} question = {this.state.question}
                        optionA = {this.state.optionA} optionB = {this.state.optionB}
                        optionC = {this.state.optionC} optionD = {this.state.optionD}
            />
            <Dialog
              title="Field Empty"
              actions={actions}
              modal={false}
              open={this.state.openAlert}
              onRequestClose={this.handleClose}
              contentStyle= {style.dialog}
            >
              please fill all the fields
            </Dialog>
        </Drawer>
        <Drawer width={style.drawer.width} openSecondary={true} open={this.state.tf} >
          <AppBar title="True/False Question" />
          < TFQuestion onSubmit = {this.getQuestion} getAnswer = {this.getAnswer}/>
            <RaisedButton label="cancel" default={true} style={style} onClick = {this.props.setDefault }/>
            <RaisedButton label="preview" secondary={true} style={style} onClick={this.openPreview}/>
            <RaisedButton label="save" primary={true} style={style} onClick = {this.save} />
            <PreviewTrueFalse open = {this.state.preview} setDefault = {this.closePreview} question = {this.state.question} />
        </Drawer>

        <Snackbar
          open={this.state.openSnackBar}
          message="Question saved"
          autoHideDuration={4000}
          onRequestClose={this.handleRequestClose}
        />
      </div>
    );
  }
  //Props received from the child components
  componentWillReceiveProps(newProps){
    this.setState({mcq: newProps.openMcq});
    this.setState({tf: newProps.openTf});
    this.setState({typeValue: newProps.type});
    this.setState({levelValue: newProps.level});
    this.setState({domainValue: newProps.domain});
    this.setState({complexityValue: newProps.complexity});
    this.setState({topicValue: newProps.topic});
    this.setState({marks: newProps.marks});

    if(this.state.domainValue === 1){
      this.setState({domain: "java"});
    }
    else if(this.state.domainValue === 2){
      this.setState({domin: "python"});
    }
    else if(this.state.domainValue ===3){
      this.setState({domain: "database"});
    }
    if(this.state.topicValue ===1){
      this.setState({topic: "Strings"});
    }
    else if(this.state.topicValue ===2){
      this.setState({topic: "collections"});
    }
    if(this.state.complexityValue ===1){
      this.setState({complexity: "easy"});
    }
    else if(this.state.complexityValue ===2){
      this.setState({complexity: "medium"});
    }
    else if(this.state.complexityValue ===3){
      this.setState({complexity: "hard"});
    }
    if(this.state.levelValue === 1) {
      this.setState({level: "l1"});
    }
    else if(this.state.levelValue === 2) {
      this.setState({level: "l2"});
    }
    else if(this.state.levelValue === 3) {
      this.setState({level: "l3"});
    }
    if(this.state.typeValue === 1){
      this.setState({type : "mcq"});
    }
    else if(this.state.typeValue === 2){
      this.setState({type : "tf"});
    }
  }
  openPreview(){
        if(this.state.type === 'mcq'){
    if(!(((this.state.question).length >=1) && ((this.state.optionA).length) >=1 && ((this.state.optionB).length) >=1
           && ((this.state.optionC).length) >=1  && ((this.state.optionD).length) >=1  && ((this.state.answer).length) >=1)
     ){
      this.setState({openAlert: true});
      return
    }
  }
    if(this.state.type === 'tf'){
      if(((this.state.question).length <1) || ((this.state.answer).length) <1 )
      {
        this.setState({openAlert: true});
        return
      }

    }
    this.setState({preview: true});
  }
  closePreview(){
    this.setState({preview: false});
  }
  getQuestion(value){
    this.setState({question: value});
  }
  getOptionA(value){
    this.setState({optionA: value});
  }
  getOptionB(value){
    this.setState({optionB: value});
  }
  getOptionC(value){
    this.setState({optionC: value});
  }
  getOptionD(value){
    this.setState({optionD: value});
  }
  getAnswer(value){
    this.setState({answer: value});
  }
  save(){
    if(this.state.type === 'mcq'){
if(!(((this.state.question).length >=1) && ((this.state.optionA).length) >=1 && ((this.state.optionB).length) >=1
       && ((this.state.optionC).length) >=1  && ((this.state.optionD).length) >=1  && ((this.state.answer).length) >=1)
 )

 {
  this.setState({openAlert: true});
  return
}
}


if(this.state.type === 'tf'){
  if(((this.state.question).length === 0) || ((this.state.answer).length) === 0 )
  {
    this.setState({openAlert: true});
    return
  }


}
    const questionDetails= {

    "subjectLists":[
        {"subject":this.state.domain,
        "topicList":[
            {
            "topic":this.state.topic,
            "levelList":[
                {
                    "level":this.state.level,
                    "complexityList":[
                        {
                            "complexity":this.state.complexity,
                            "questionTypeList":[
                                {
                                    "questionType":this.state.type,
                                    "questionList":[
                                        {
                                            "id":"8",
                                            "question":this.state.question,
                                            "options":[{"a":this.state.optionA,"b":this.state.optionB,"c":this.state.optionC,"d":this.state.optionD}],
                                            "correctAnswer":this.state.answer,
                                            "marksAlloted":this.state.marks
                                        }]
                                }]
                        }
                        ]
                }]
            }]

       }
    ]
}
  request
  .post('http://172.23.239.163:8079/api/questionbank/questions')
  .set('content-type', 'application/json')
  .send(questionDetails)
  .end((res,err) =>{
    if(res){
      console.log("res -" ,res.body);
    }
    else{
      console.log("error -> ", err);
    }
  })
  this.setState({openSnackBar: true});


  }
  handleRequestClose = () => {
  this.setState({
    openSnackBar: false,
  });
};
handleClose = () => {
  this.setState({openAlert: false});
};




}
