import React, { Component } from 'react';


class Counter extends Component {
 constructor (props) {
    super(props);
    this.state = { hh : props.hh,
                  mm:props.mm,
                  ss:props.ss
                   }
  }

  render() {
    var x = this;
    var { hh, mm, ss } = this.state;
                  setTimeout(function() {
                    if (ss > 0 && ss < 60 ) {
                      x.setState({ ss: ss - 1 });
                    }else{
                      if(mm > 0 && mm < 60 ){
                        x.setState({mm:mm-1});
                        x.setState({ss: 59});
                      }else{
                        if(hh >= 0 && hh < 60 ){
                          x.setState({ss:59});
                          x.setState({mm:59});
                          x.setState({hh:hh-1});

                        }else{

                        }
                      }
                    }
                  }, 1000);
    return <div><h4>Time Remainning: {hh}:{mm}:{ss}</h4></div>;
  }
}



export default Counter;
