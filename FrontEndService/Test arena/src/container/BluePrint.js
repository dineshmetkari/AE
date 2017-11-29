import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import request from 'superagent';
import Snackbar from 'material-ui/Snackbar';
import {Link} from 'react-router';

let obj={};

let data=[];
let finalData=[];
let answer=[];
let value=[];
var obj1;
class BluePrint extends Component{
	constructor(props)
	{
	    super(props);
	    this.saveIntoDatabse = this.saveIntoDatabse.bind(this);
		this.state = {data : this.props.vars}
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

	render() {
				 const {data} =this.state;
				 				finalData=this.props.vars

				 value=JSON.stringify(finalData);
         obj1=JSON.parse(value);
				 console.log("questions data"+data)
				 var questions=data.map(question =>{
				 	return(
				 			<div>
				 			<h3>Subject:{question.subject},-Level:{question.level},-Complexity:{question.complexity}</h3>
				 			<h3>Question:{question.question}</h3>
				 			</div>
				 		)
				 });
         let length=questions.length;

				 return	(
				 	<div><div><h1>Blue print created based on your requirement</h1>
          <h2>Number of questions added:{length}</h2></div>
				 	<div>
				 	  {questions}
				 	  </div>
				 	  <div>
				 	 <RaisedButton label=" Save question paper" primary={true} onClick={this.saveIntoDatabse} />
					 <RaisedButton label=" cancel" secondary={true} containerElement={<Link to="/searchq"/>}/>
					 </div>

					<Snackbar
			          open={this.state.open}
			          message="Question paper saved successfully...!"
			          autoHideDuration={4000}
			          onRequestClose={this.handleRequestClose}
			        />

					</div>
				 	)

			}




	 saveIntoDatabse()
	 {
	 	console.log("storing data from value"+value);
	 	let i=0;
	     for(i=0;i<=30;i++)
	 		{
	 			request
	 			.post('http://172.23.239.165:8074/create')
      //  .post('http://172.23.238.217:8074/create')
        //.post('http://172.23.238.225:8079/api/exampaperCreation/create')
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
	 		this.handleTouchTap();
	 }
}

export default BluePrint;
// export default CardExampleExpandable;
