import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import StartExam from "./StartExam";

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
        <StartExam />
      </MuiThemeProvider>
    );
  }
}

export default App;
