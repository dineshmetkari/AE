import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import request from 'superagent';

let obj={};

let data=[];
let answer=[];
let value=[];
var obj1;
class BluePrint extends Component{
	constructor(props) 
	{	
	    super(props); 
	    this.saveIntoDatabse = this.saveIntoDatabse.bind(this);
		this.state = {data : null}
    }
	render() {
				 data =this.props.vars
				  value = JSON.stringify(data);
				 obj1=JSON.parse(value);
				    return (
						      <div>
						      <h4>{value}</h4>
						     <RaisedButton label="Save Paper" default={true} onClick={this.saveIntoDatabse} />
						      </div>
				   			 );
					
					return(
					<div>
					{results}
					</div>
					);
 }

	 saveIntoDatabse()
	 {
	 	console.log("storing data"+value);
	 	let i=0;
	     for(i=0;i<=5;i++)
	 		{
	 			request
	 			.post('http://localhost:8074/create')
	 			.set('Content-type', 'application/json')
				.send(obj1[i])
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
	 		alert("Data saved successfully......!");
	 }
}

export default BluePrint;