import React from 'react';
import ReactDom from 'react-dom';
import './index.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {HashRouter, Route} from 'react-router-dom'
import AppBar from './navBar'
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
    <Route path="/" component= {Index} />
    </div>
    </HashRouter>
,document.getElementById('root'));
registerServiceWorker();
