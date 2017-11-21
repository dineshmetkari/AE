import React from 'react';
import {Link} from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';
import axios from 'axios';

const style = {
  margin: 8,
 marginLeft: 600
};
let obj={};
let res=[];
export default class ResultsHome extends React.Component{
	constructor(props){
		super(props);
		this.state={data: [],
			content:null
		}
		this.onSubmit=this.getResult.bind(this);
		this.onSubmit= this.onSubmit.bind(this);
	}
  getResult(){
		var x=this;
				let result=[];
          fetch('http://localhost:8086/results/sai@gmail.com/',{
          method: 'GET'

          })
          .then(function(response) {
          return response.json()
          }).then(function(body) {
          	console.log("jjj"+body);
					 result.push(body);
					 console.log(result);

					 result.map(function(x){

					 	obj={'TotalExamMarks':x.totalExamMarks,'TotalUserObtainedMarks':x.totalMarksobtained};
						res.push(obj);

					 })
					 console.log("hhhhhhhhhhhhhhhhhhhhhhh"+JSON.stringify(res));
					 x.setState({content:JSON.stringify(body)});
        });
  }
onSubmit(){
	this.getResult();
}


	render(){
		var {content} = this.state;

													if(content){
													return(
														<div>
											      <RaisedButton label="View Results" primary={true} onClick={this.onSubmit}/>
														<h2>Result:</h2>
														<h4>Total Maximum Marks : {JSON.parse(content).totalExamMarks}</h4>
														<h4>Marks Secured : {JSON.parse(content).totalMarksobtained}</h4>
														 </div>

														);
												}else{
												return(
													<div>
													<RaisedButton label="View Results" primary={true} onClick={this.onSubmit}/>
													 </div>
													);
											}
						}
					}
