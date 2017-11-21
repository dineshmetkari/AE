import Dropdown from 'react-timer';
import ReactDOM from 'react-dom';
import React, { Component, PropTypes } from 'react';
import Counter from './Counter';


const divStyle={
  width:'1000px',
  margin:'auto',
  textAlign:'center'
}
class ExamTimer extends Component{
  constructor(props){
    super(props);
    this.state={

    }
  }

  getTimerFlag(flag){
    this.props.sendTimerFlag(flag);
  }
  render () {
    return (
      <div style={divStyle}>
       <Counter hh={1} mm={30} ss={0} sendTimerFlag={this.getTimerFlag.bind(this)}/>
      </div>
    )
  }
}

export default ExamTimer;

