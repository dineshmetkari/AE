import React, { Component } from 'react';
import Paper from 'material-ui/Paper';
class Pagination extends Component{

		
	constructor(props){
		super(props);
		this.state ={
			QuestionCount:this.props.questionCount,
		}
		this.handleClick=this.handleClick.bind(this);
	}

	handleClick(event){
		this.props.onPagerClick(Number(event.target.id));
	}

 render(){
 	const {QuestionCount} = this.state;
 	const QuestionNumbers=[];
 	for(var i=1; i<= QuestionCount; i++){QuestionNumbers.push(i);}

 	const renderQuestionNumbers = QuestionNumbers.map(QuestionNumber => {
          return (
            <li
              key={QuestionNumber}
              id={QuestionNumber}
              onClick={this.handleClick}
              style={{ListStyleType:'none', display:'inline-block',width:'20px',textAlign:'center', padding:'5px', backgroundColor:'#00bcd4',color:'white', border:'2px solid #00bcd4', borderRadius:'50px', boxShadow:'0px 0px 3px #888888', margin:'2px' }}
            >
              {QuestionNumber}
            </li>
          );
        });
        	
 	return(
 		<div>
 		<ul style={{cursor:'pointer'}}>
              {renderQuestionNumbers}
            </ul>
 		</div>
 	);
 }
}

export default Pagination;
