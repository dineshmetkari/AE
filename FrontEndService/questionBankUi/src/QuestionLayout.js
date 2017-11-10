import React from 'react';
import Drawer from 'material-ui/Drawer';
import axios from 'axios';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import McqQuestion from './McqQuestion';
import TFQuestion from './TFQuestion';
import PreviewTrueFalse from './PreviewTrueFalse';
const style = {
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
                  topic: 'CCCC',
                  question: '',
                  answer: '',
                  optionA: '',
                  optionB: '',
                  optionC: '',
                  optionD: ''}
    this.openPreview = this.openPreview.bind(this);
    this.closePreview =  this.closePreview.bind(this);
    this.getQuestion = this.getQuestion.bind(this);
    this.getAnswer = this.getAnswer.bind(this);
    this.save = this.save.bind(this);
    this.getOptionA = this.getOptionA.bind(this);
    this.getOptionB = this.getOptionB.bind(this);
    this.getOptionC = this.getOptionC.bind(this);
    this.getOptionD = this.getOptionD.bind(this);
  }
  render() {
    return (
      <div>
        <Drawer width={1439} openSecondary={true} open={this.state.mcq} >
          <AppBar title="MCQ Question" />
          < McqQuestion onSubmit = {this.getQuestion} getAnswer = {this.getAnswer}
                        optionA = {this.getOptionA} optionB = {this.getOptionB}
                        optionC = {this.getOptionC} optionD = {this.getOptionD}
          />
            <RaisedButton label="cancel" default={true} style={style} onClick = {this.props.setDefault }/>
            <RaisedButton label="preview" secondary={true} style={style} />
            <RaisedButton label="save" primary={true} style={style} />

        </Drawer>
        <Drawer width={1439} openSecondary={true} open={this.state.tf} >
          <AppBar title="True or False Question" />
          < TFQuestion onSubmit = {this.getQuestion} getAnswer = {this.getAnswer}/>
            <RaisedButton label="cancel" default={true} style={style} onClick = {this.props.setDefault }/>
            <RaisedButton label="preview" secondary={true} style={style} onClick={this.openPreview}/>
            <RaisedButton label="save" primary={true} style={style} onClick = 'https:/172.23.238.133:3000' />
            <PreviewTrueFalse open = {this.state.preview} setDefault = {this.closePreview} question = {this.state.question} />
        </Drawer>
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

    if(this.state.domainValue === 1){
      this.setState({domain: "java"});
    }
    if(this.state.domainValue === 2){
      this.setState({domin: "python"});
    }
    if(this.state.domainValue ===3){
      this.setState({domain: "database"});
    }
    if(this.state.topicValue ===1){
      this.setState({topic: "Strings"});
    }
    if(this.state.topicValue ===2){
      this.setState({topic: "collections"});
    }
    if(this.state.complexityValue ===1){
      this.setState({complexity: "easy"});
    }
    if(this.state.complexityValue ===2){
      this.setState({complexity: "medium"});
    }
    if(this.state.complexityValue ===3){
      this.setState({complexity: "hard"});
    }
  }
  openPreview(){
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
    let type = this.state.typeValue;
    let level =  this.state.levelValue;
    let domain = this.state.DomainValue;
    let complexity = this.state.complexityValue;
    let topic = this.state.topicValue;
    let question = this.state.question;
    let answer = this.state.answer;
  }
}
