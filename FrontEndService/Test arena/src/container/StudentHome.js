 import React from 'react';
import {Link} from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';
import {browserHistory} from 'react-router';

import ResultLogo from './ic_assessmentresult_white_48px.svg';
import ExamLogo from './ic_assignmentexam_white_48px.svg';
import './StudentHome.css';

export default class  StudentHome extends React.Component {
  constructor(props){
    super(props);

    this.state={
    name : '',
    }
   }
   componentDidMount(){

        var name = this.props.location.state;
        this.setState({user : name});
      }
   handleText() {
  // this works. path updates and renders new component

    browserHistory.push({pathname : '/results', state : this.state.user});
  }
  handleUpdateprofile() {
 // this works. path updates and renders new component

   browserHistory.push({pathname : '/updatestudentprofile', state : this.state.user});
 }

 handleStartexam() {
// this works. path updates and renders new component

  browserHistory.push({pathname : '/startexam', state : this.state.user});
}

  render(){
    var name = this.props.location.state;
    console.log(name);
    return(
      <div>
      <div style={userStyle}>
      <h3>Logged In as : {name}</h3>
      </div>

      <div style={linkStyle}>
      <Link href="/logout">
          <RaisedButton label="Logout" primary={true} style={buttonStyle}/>
      </Link>
      <br/>

<div style={{display:'flex', flexDirection:'row', justifyContent:'space-around', marginTop:'20vh'}}>


      <Link onClick={this.handleStartexam.bind(this)} >
     <div className='tabs'>
        <div className='logos'>
          <img src = {ExamLogo} alt = "Exam Logo"/>
        </div>

        <h2 style={{color:'white'}}>Take Exam</h2>

      </div>
      </Link>



      <Link onClick={this.handleText.bind(this)}>
      <div className='tabs'>

        <div className='logos'>
          <img src={ResultLogo} alt="Result Logo" />
        </div>
        <h2 style={{color:'white'}}>Results</h2>
      </div>
      </Link>

      <Link onClick={this.handleUpdateprofile.bind(this)}>
      <div className='tabs'>

        <div className='logos'>
          <img src={ResultLogo} alt="Result Logo" />
        </div>
        <h2 style={{color:'white'}}>Update Profile</h2>
      </div>
      </Link>
</div>
        </div>
      </div>
    )
  }
}

const linkStyle = {
  margin:'',
 marginLeft:''
};

const buttonStyle = {
  marginTop : 8,
  position:'relative',
  marginLeft: "85%"
};

const userStyle = {
  position: 'absolute',
  display : 'inline',
  marginLeft : 25
}
