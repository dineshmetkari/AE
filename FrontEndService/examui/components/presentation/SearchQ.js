import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import axios from 'axios';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import request from 'superagent';
import {Link} from 'react-router';

import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import AutoComplete from 'material-ui/AutoComplete';
import lodash from 'lodash';

let encoding = 'utf8';    
let answer =[];
let obj={};
let answer2=[];
let subjectObj={};
let topicObj={};
let topicAnswer=[];
class SearchQ extends React.Component{

  constructor(props) {
    super(props);
    this.state = {
      value: undefined,
      topicValue : undefined,
      studentData: [],
       topicData:[],
      subject:undefined,
      topic:undefined


    };
  }
componentDidMount=()=>{
  let url="http://172.23.238.205:8081/questions";

  request
   .get(url)
   .end((err, res) => {
     if(err){
       console.log("error in getting");
     } else {
       let info = res.body;
       let arr1 = [];
        let arr2 = [];
         info.forEach((subjectInfo) => {
            subjectInfo.subjectLists.forEach((subjectName) => {
              arr1.push(subjectName.subject);
              subjectName.topicList.forEach((topicName) => {
                arr2.push(topicName.topic);
            })
           })
         });
      let uniqueSubject = lodash.uniq(arr1);
      let uniqueTopic = lodash.uniq(arr2);
      console.log("array", uniqueSubject, "array2", uniqueTopic);
      this.setState({
        studentData : uniqueSubject,
        topicData : uniqueTopic
      })
     }
   })
 }

  handleChange = (event, index, value) => this.setState({value : value});

  handleChangeTopic = (event, index, topicValue) => this.setState({topicValue : topicValue});



getOuestions = () => {
  let subject=this.state.value;
  let topic=this.state.topicValue;
}
  render() {
    return (
  <div style={{paddingLeft:25}}>
      <h2>Give Requirement to Create Quetsion Paper </h2>
      <div>
      <TextField ref='s7' hintText="name of QP" floatingLabelText="Question Paper Name" style={{ width: '30%',marginRight:8}} /><br/>
        <h3 style={{paddingLeft:25}}>Select Subject <span style={{paddingLeft:240}}>Select Topic</span></h3>
          <DropDownMenu value={this.state.value} onChange={this.handleChange} openImmediately={false} style={{ width: '19%',paddingLeft:-100}}>
          {(this.state.studentData.length >= 1) ? 
            this.state.studentData.map((e, i) => {
              return <MenuItem value={i} primaryText={e} />
            }): ""}
          </DropDownMenu>
      
          <DropDownMenu value={this.state.topicValue} onChange={this.handleChangeTopic} openImmediately={false} style={{ width: '19%',marginRight:'0%'}}>
          {(this.state.topicData.length >= 1) ? 
            this.state.topicData.map((e, i) => {
              return <MenuItem value={i} primaryText={e} />
            }): ""}
          </DropDownMenu>
    </div><br />   
    <div><TextField  ref='s3' hintText="Ex:l1,l2,l3" floatingLabelText="Level" style={{width: '20%',marginRight:24}} />
      <TextField ref='s4' hintText="Ex:easy,hard" floatingLabelText="Coplexity" style={{ width: '20%',marginRight:8}} />
      <TextField  ref='s5' hintText="Ex:mcq,trueOrfalse" floatingLabelText="Question type" style={{width: '20%',marginRight:24}} />
      <TextField ref='s6' hintText="Ex:5,10,2,7" floatingLabelText="Number Of Questions" style={{ width: '20%',marginRight:8}} />
      

    </div>     
    <div style={{marginTop:20,marginLeft:4}}> 
          <RaisedButton style={{marginRight:16}} label="Add More" primary={true} onClick={this.add.bind(this)} />
          <RaisedButton label="Blue Print" primary={true} onClick={this.blueprint} />
         
     </div>
  </div>
    );
  }

add()
 {

    let a = this.state.studentData[this.state.value];
    let b = this.state.topicData[this.state.topicValue];
    let c = this.refs.s3.getValue();
    let d =this.refs.s4.getValue();
    let e = this.refs.s5.getValue();
    let f =this.refs.s6.getValue();
    let paperName=this.refs.s7.getValue();
    console.log(c+d+e+f+paperName);

    let ur=`http://172.23.238.205:8081/specquestions/${a}/${b}/${c}/${d}/${e}/${f}`; 
    console.log("ur", ur);
    
     axios.get(ur).then((response) =>
    {
       var myJsonString = JSON.stringify(response.data);
        response.data.map(function(x) 
      {
        var optionsIntoarry=Object.values((x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionList[0].options[0]));
        obj=
        {
          'questionId': paperName,
          'subject': x.subjectLists[0].subject ,
          'topic':x.subjectLists[0].topicList[0].topic ,
          'level':x.subjectLists[0].topicList[0].levelList[0].level,
          'complexity':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].complexity,
          'questionType':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionType,
          'question':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionList[0].question,
          'options':optionsIntoarry,
          'correctAnswer':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionList[0].correctAnswer,
            'marksAlloted':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionList[0].marksAlloted             
        }
        answer.push(obj); 
      });      
    })
    .catch( (error) => 
    {
      console.log(error);
    });  
  }
  blueprint()
  {
  
      this.props.questionData(answer);
      console.log(answer+"done"); 
  }



}
export default SearchQ;