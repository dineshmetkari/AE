import React from 'react';
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';
import SelectField from 'material-ui/SelectField';
import RaisedButton from 'material-ui/RaisedButton';
import axios from 'axios';

import TextField from 'material-ui/TextField';
// const persons = [
//   {value: 0, name: 'Inthiyaz'},
//   {value: 1, name: 'Jan'},
//   {value: 2, name: 'Raju'},
//   {value: 3, name: 'Sai'},
//   {value: 4, name: 'Ram'}
  
// ];

let obj={};
let answer=[];
export default class Papaers extends React.Component
{
	constructor(props)
	{
		super(props);
		
		this.students = this.students.bind(this);
		this.state = {data : null}
		
	}

render()
{
	return(
		<div>
		<h3>Enter Skill to set the Exam for Student</h3>
    <TextField  ref='s1' hintText="Ex:java,c#" floatingLabelText=" SkillSet" style={{width: '45%',marginRight:24}} />
    <RaisedButton label="Submit" primary={true} onClick={this.students} />
    <p>{this.state.answer}</p>
		</div>

		)
}

students()
	{
		let a = this.refs.s1.getValue();
		 
		let ur='http://172.23.238.205:8083/specquestions/'+a;	
		
		 axios.get(ur).then((response) =>
		{
			 var myJsonString = JSON.stringify(response.data);
			  response.data.map(function(x)	
			{
				
				obj=
				{
					'Student_Email':x.emailId,
					'Student_Name':x.studentName
			
				}
				answer.push(obj);	
			});
			 
			  
			  
   			// this.props.fun1(answer);				
		})
		.catch( (error) => 
		{
			console.log(error);
		}); 
		
	}



}
