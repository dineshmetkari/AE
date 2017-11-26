import React from 'react';
import TextField from 'material-ui/TextField';
import RaisedButton from 'material-ui/RaisedButton';
import axios from 'axios';
import request from 'superagent';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import lodash from 'lodash';
import SelectField from 'material-ui/SelectField';

let answer =[];
let obj={};

class SearchQ extends React.Component{

  constructor(props) {
    super(props);
   // this.handlechange1=this.handlechange1.bind(this)
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
      subject:undefined,
      topic:undefined,
    selectedStudents: []


    };
  }
componentWillMount=()=>{
     let url="http://172.23.239.157:8081/questions";

  let studenturl="http://172.23.239.157:8083/students";

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
     });
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

                console.log("getting data");

               arr.push(studentObj);
               console.log(arr);

                })

                this.setState({studentInfo: arr})
                 console.log("State changed"+this.state.studentInfo);

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


  handleChange = (event, index, value) => this.setState({value : value});

  handleChangeTopic = (event, index, topicValue) => this.setState({topicValue : topicValue});

handleStudentChange = (event, index, studentValues) => this.setState({studentValues});

// getOuestions = () => {
//   let subject=this.state.value;
//   let topic=this.state.topicValue;
// }
  render() {
    console.log(this.state.values);
    return (
  <div style={{paddingLeft:25}}>
      <h2>Give requirement to create question paper </h2>
      <div>
      <TextField ref='s7' hintText="name of QP" floatingLabelText="Question paper name" style={{ width: '20%',marginRight:8}} /><br/>
        <h4 style={{paddingLeft:25}}>Select subject <span style={{paddingLeft:40}}>Select topic</span><span style={{paddingLeft:40}}>Select students</span></h4>

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
           <SelectField
        multiple={true}
        hintText="Select students"
        value={this.state.values}
        onChange={this.handle}
        selectionRenderer={this.selectionRenderer}
        style={{ width: '19%',marginTop:'1%'}} >
        {this.menuItems(this.state.studentInfo)}
      </SelectField>
    </div>
    <br />

    <div><TextField  ref='s3' hintText="Ex:l1,l2,l3" floatingLabelText="Level" style={{width: '20%',marginRight:24}} />
      <TextField ref='s4' hintText="Ex:easy,hard" floatingLabelText="Complexity" style={{ width: '20%',marginRight:8}} />
      <TextField  ref='s5' hintText="Ex:mcq,trueOrfalse" floatingLabelText="Question type" style={{width: '20%',marginRight:24}} />
      <TextField ref='s6' hintText="Ex:5,10,2,7" floatingLabelText="Number Of questions" style={{ width: '20%',marginRight:8}} />


    </div>
    <div style={{marginTop:20,marginLeft:4}}>
          <RaisedButton style={{marginRight:16}} label="Add More" primary={true} onClick={this.add.bind(this)} />
          <RaisedButton label=" view Blue Print" primary={true} onClick={this.blueprint} />

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

    let ur=`http://172.23.239.157:8081/specquestions/${a}/${b}/${c}/${d}/${e}/${f}`;

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
    alert("Questions added successfully");
  }
  blueprint()
  {

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
      console.log(arrayOfStudents);

    }
  console.log(answer);
      this.props.questionData(answer);
      console.log("done");

      console.log("in BliuePrint==============="+arrayOfStudents)

      var stuobj={};
      stuobj['examName']=this.refs.s7.getValue();
      stuobj['studentsName']=arrayOfStudents;

       console.log("students storing");

        request
        .post('http://172.23.238.217:8074/students')
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

    console.log("saved exam");
  }



}
export default SearchQ;
