import React, { Component } from 'react';


class Counter extends Component {
 constructor (props) {
    super(props);
    this.state = { hh : props.hh,
                  mm:props.mm,
                  ss:props.ss,
                  TimeUpFlag: null,
                   }
                   this.setTimerFlag= this.setTimerFlag.bind(this);
  }
setTimerFlag(msg){
  this.props.sendTimerFlag(msg);
}
  render() {
    var x = this;
    var { hh, mm, ss , TimeUpFlag , var1 } = this.state;
                  setTimeout(function() {
                    if(hh == 0 && mm == 0 && ss == 0){
                      x.setTimerFlag("Timeup");
                    }else{
                      if(mm == 0 && ss ==0){
                        x.setState({hh: hh -1, mm: 59, ss: 59});
                      }else{
                      if(ss == 0 ){
                        x.setState({mm: mm -1, ss: 59});
                      }else{
                        x.setState({ss:ss-1});
                      }
                    }
                  }
                  }, 1000);
    return <div><h4>Time Remainning: {hh}:{mm}:{ss}</h4><p>{this.state.var1}</p></div>;
  }
}
      


export default Counter;


