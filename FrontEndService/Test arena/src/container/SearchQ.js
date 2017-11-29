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
import SelectField from 'material-ui/SelectField';
import Snackbar from 'material-ui/Snackbar';

let encoding = 'utf8';
let answer =[];
let obj={};
let answer2=[];
let subjectObj={};
let topicObj={};
let topicAnswer=[];
let studentObj={};
class SearchQ extends React.Component{

  constructor(props) {
    super(props);
    this.add = this.add.bind(this);
    this.state = {data : null}
    this.blueprint = this.blueprint.bind(this);
    this.state = {data : null}
    this.state = {
      value: undefined,
      topicValue : undefined,
      studentData: [],
      studentInfo: [],
      selectednames: [],
      values: [],
      topicData:[],
      levelData : [],
      complexityData : [],
      questionTypeData : [],
      subject:undefined,
      topic:undefined,
      selectedStudents: [],
      open: false
    };
  }
 handleTouchTap = () => {
    this.setState({
      open: true,
    });
  };

  handleRequestClose = () => {
    this.setState({
      open: false,
    });
  };
componentWillMount=()=>{
    // let url="http://172.23.238.205:8081/questions";
    // let url="http://172.23.238.205:8081/questions";
     let url="http://172.23.239.163:8079/api/questionbank/questions";
    // let studenturl="http://172.23.238.205:9999/students";
      //let studenturl="http://172.23.238.205:9999/students";
       let studenturl="http://172.23.239.163:8079/api/student/students";

    request
     .get(url)
     .end((err, res) => {
       if(err){
         console.log("error in getting");
       } else {
         let info = res.body;
         let subjectFromQb = [];
          let topicFromQb = [];
          let levelFromQb = [];
          let typeOfQestionFromQb = [];
          let complexityFromQb = [];

           info.forEach((subjectInfo) => {
              subjectInfo.subjectLists.forEach((subjectName) => {
                subjectFromQb.push(subjectName.subject);
                subjectName.topicList.forEach((topicName) => {
                  topicFromQb.push(topicName.topic);

                  topicName.levelList.forEach((levelName) => {
                  levelFromQb.push(levelName.level);
                   levelName.complexityList.forEach((complexityName) => {
                  complexityFromQb.push(complexityName.complexity);
                  complexityName.questionTypeList.forEach((questionTypeName) => {
                  typeOfQestionFromQb.push(questionTypeName.questionType);
                  })
                  })
                })
              })
             })
           });
        let uniqueSubject = lodash.uniq(subjectFromQb);
        let uniqueTopic = lodash.uniq(topicFromQb);
        let uniqueLevel = lodash.uniq(levelFromQb);
        let uniqueComplexity = lodash.uniq(complexityFromQb);
        let uniqueQuestionType = lodash.uniq(typeOfQestionFromQb);

        this.setState({
          studentData : uniqueSubject,
          topicData : uniqueTopic,
          levelData : uniqueLevel,
          complexityData  : uniqueComplexity,
          questionTypeData : uniqueQuestionType

        })
       }
     });
console.log("leveldata");
     console.log(this.state.levelData);
          request
          .get(studenturl)
          .end((err, res) =>{
            if(err){
              console.log("Error while getting data from student..!");
            }else{
                let allStudent=res.body;
                var arr=[];
                var i=0;
                allStudent.map(function(st){
                  var studentObj={};
                studentObj['value']= i;
                studentObj['name']=st.emailId;
                i++;
               arr.push(studentObj);
                })

                this.setState({studentInfo: arr})
            }
          })
 }
 selectionRenderer = (values) => {
    switch (values.length) {
      case 0:
        return '';

      default:
        return `${values.length} names selected`;
    }
  }
handle = (event, index, values) => this.setState({values});
menuItems(studentInfo) {
    return studentInfo.map((email) => (
      <MenuItem
      key={email.value}
      insetChildren={true}
      checked={this.state.values.indexOf(email.name) > -1}
        value={email.value}

        primaryText={email.name}
      />
    ));
  }
  handleChange = (event, index, value) =>{
    this.setState({value : value});
    let t=this.state.studentData[this.state.value];
    console.log(this.state.studentData[value]);
    console.log(value);
    let surl='http://172.23.239.157:8081/specsubject/'+this.state.studentData[value];
    console.log(surl);
    request
     .get(surl)
     .end((err, res) => {
         if(err){
           console.log("error in getting");
         } else {
                 let info1 = res.body;
                 let subjectFromQb = [];
                  let topicFromQb = [];

                  console.log("info from db"+info1)
           info1.forEach((subjectInfo) => {
              subjectInfo.subjectLists.forEach((subjectName) => {
                subjectFromQb.push(subjectName.subject);
                subjectName.topicList.forEach((topicName) => {
                  topicFromQb.push(topicName.topic);
              })
             })
           });

        let uniqueTopic = lodash.uniq(topicFromQb);
        console.log("topic data for subject===="+uniqueTopic)

        this.setState({

          topicData : uniqueTopic


        });

    }

    });
   }

  handleChangeTopic = (event, index, topicValue) => this.setState({topicValue : topicValue});
  handleChangeLevel = (event, index, levelValue) => this.setState({levelValue : levelValue});
  handleChangeComplexity = (event, index, complexityValue) => this.setState({complexityValue : complexityValue});
  handleChangeQuestionType = (event, index, questionTypeValue) => this.setState({questionTypeValue : questionTypeValue});
  handleStudentChange = (event, index, studentValues) => this.setState({studentValues});

          getOuestions = () => {
            let subject=this.state.value;
            let topic=this.state.topicValue;
          }
render() {
  return (

  <div style={{width:"900px" , margin:'0 auto'}}>
      <h1>Give requirement to create question paper </h1>
      <div>
        <TextField ref='s7' hintText="Question paper name" floatingLabelText="Question paper name" style={{ width: '25%',marginRight:5}} /><br/>

         <SelectField
            multiple={true}
            hintText="Select students"
            value={this.state.values}
            onChange={this.handle}
            selectionRenderer={this.selectionRenderer}
            style={{ width: '25%',marginTop:'1%'}} >
            {this.menuItems(this.state.studentInfo)}
         </SelectField>
      </div>
      <div>
          <h4>Select subject and topic</h4>
          <DropDownMenu value={this.state.value} onChange={this.handleChange} openImmediately={false} style={{ width: '19%',marginRight:5}}>
            {(this.state.studentData.length >= 1) ?
              this.state.studentData.map((e, i) => {
                return <MenuItem value={i} primaryText={e} />
              }): ""}
          </DropDownMenu>
          <DropDownMenu value={this.state.topicValue} onChange={this.handleChangeTopic} openImmediately={false} style={{ width: '19%'}}>
          {(this.state.topicData.length >= 1) ?
            this.state.topicData.map((e, i) => {
              return <MenuItem value={i} primaryText={e} />
            }): ""}
          </DropDownMenu>
      </div>
      <div>
          <h4>Choose complexity and question type </h4>
          <DropDownMenu value={this.state.levelValue} onChange={this.handleChangeLevel} openImmediately={false} style={{ width: '19%',marginRight:'0%'}}>
            {(this.state.levelData.length >= 1) ?
              this.state.levelData.map((e, i) => {
                return <MenuItem value={i} primaryText={e} />
              }): ""}
          </DropDownMenu>
         <DropDownMenu value={this.state.complexityValue} onChange={this.handleChangeComplexity} openImmediately={false} style={{ width: '19%',marginRight:'0%'}}>
            {(this.state.complexityData.length >= 1) ?
              this.state.complexityData.map((e, i) => {
                return <MenuItem value={i} primaryText={e} />
              }): ""}
          </DropDownMenu>
          <DropDownMenu value={this.state.questionTypeValue} onChange={this.handleChangeQuestionType} openImmediately={false} style={{ width: '19%',marginRight:'0%'}}>
              {(this.state.questionTypeData.length >= 1) ?
                this.state.questionTypeData.map((e, i) => {
                  return <MenuItem value={i} primaryText={e} />
                }): ""}
          </DropDownMenu>
      </div>
      <br />
          <TextField ref='s6' hintText="Ex:1,3,10,20" floatingLabelText="Number of questions" style={{ width: '17%',marginRight:8}} />
        <div style={{marginTop:20,marginLeft:4}}>
          <RaisedButton style={{marginRight:16}} label="Add More" primary={true} onClick={this.add.bind(this)} />
          <RaisedButton label=" Preview" primary={true} onClick={this.blueprint} />
    </div>
        <Snackbar
          open={this.state.open}
          message="Added to blueprint if you want more questions select addmore otherwise click on blueprint"
          autoHideDuration={6000}
          onRequestClose={this.handleRequestClose}
         />
  </div>
    );
  }


add()
 {

    let a = this.state.studentData[this.state.value];
    let b = this.state.topicData[this.state.topicValue];
    let c = this.state.levelData[this.state.levelValue];
    let d =this.state.complexityData[this.state.complexityValue];
    let e = this.state.questionTypeData[this.state.questionTypeValue];
    let f =this.refs.s6.getValue();
    let paperName=this.refs.s7.getValue();
    console.log(c+d+e+f+paperName);

  //  let ur=`http://172.23.238.205:8081/specquestions/${a}/${b}/${c}/${d}/${e}/${f}`;
    //let ur=`http://172.23.238.205:8081/specquestions/${a}/${b}/${c}/${d}/${e}/${f}`;
    let ur=`http://172.23.239.163:8079/api/questionbank/specquestions/${a}/${b}/${c}/${d}/${e}/${f}`;

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
            'marksAllotted':x.subjectLists[0].topicList[0].levelList[0].complexityList[0].questionTypeList[0].questionList[0].marksAlloted
        }
        answer.push(obj);
      });
      this.handleTouchTap();
    })
    .catch( (error) =>
    {
      console.log(error);
      alert("Please add requirement to blueprint");

    });
  }

  blueprint()
  {
    this.props.questionData(answer);

      var that=this;
      var arrayOfStudents=[];
      for(var k=0;k<this.state.values.length;k++){
      this.state.studentInfo.map(function(obj){
                if(that.state.values[k]==obj.value)
                {
                  arrayOfStudents.push(obj.name);
                }
         })
        this.setState({selectedStudents: arrayOfStudents})
      }
        var stuobj={};
        stuobj['examName']=this.refs.s7.getValue();
        stuobj['studentsName']=arrayOfStudents;
        request
       .post('http://172.23.239.165:8074/students')
        //.post('http://172.23.238.217:8074/students')
        //.post('http://172.23.238.225:8079/api/exampaperCreation/students')
        .set('Content-type', 'application/json')
        .send(stuobj)
        .end((res, err) =>
        {
           if(res)
          {
              console.log("this is res", res.body);

          }
             else
             {
              console.log("this is err", err);
             }
        })
  }
}
export default SearchQ;
