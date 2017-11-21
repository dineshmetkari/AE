import React from 'react';
import ReactDom from 'react-dom';
import './index.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import QuestionProperties from './QuestionProperties';
import QuestionLayout from './QuestionLayout';
import {HashRouter, Route,Link} from 'react-router-dom'
import AppBar from './NavBar'
class Index extends React.Component{
  render(){
    return(
      <MuiThemeProvider>
      <AppBar />
      <App />
      </MuiThemeProvider>
    )
  }
}
ReactDom.render(
  <HashRouter>
    <div>
    <Route path="/questionsetup" component= {Index} />
      <Route path = "/questionDetails" component={QuestionLayout} />
    </div>
    </HashRouter>
,document.getElementById('root'));
registerServiceWorker();
