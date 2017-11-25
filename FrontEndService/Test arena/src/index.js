import React from 'react';
import ReactDom from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Main from './container/Main';
import ComponentNotFound from './container/ComponentNotFound';
import Register from './container/Register';
import StudentLogin from './container/StudentLogin';
import StudentHome from './container/StudentHome';
import ResultsHome from './container/ResultsHome';
import AdminLogin from './container/AdminLogin';
import HomePage from './container/HomePage';
import AdminHome from './container/AdminHome';
import UpdateStudentProfile from './container/UpdateStudentProfile';
import Main2 from './container/Main2';
import BluePrint from './container/BluePrint';
import StartExam from './enginecomponents/StartExam';
import App from './addquestionscomponent/App';
import QuestionLayout from './addquestionscomponent/questionLayout';
import SearchQ from './container/SearchQ';
import Appbar2 from './container/Appbar2';
import {browserHistory, Route, Router, IndexRoute}  from 'react-router';


class Index extends React.Component{
	render(){
		return(

			<MuiThemeProvider>
			<div>

					<Appbar2 />
					 {this.props.children}
				</div>
				</MuiThemeProvider>

			)
	}
}
ReactDom.render(
				<Router history={browserHistory}>
				<Route path="/" component={Index} >
                <IndexRoute component={Main} />
								<Route path="/register" component={Register}/>
								<Route path="/error" component={ComponentNotFound}/>
								<Route path="/studentLogin" component={StudentLogin}/>
								<Route path="/loginMain" component={StudentHome}/>
              	<Route path='/adminLogin' component={AdminLogin} />
								<Route path='/adminMain' component={AdminHome} />
								<Route path='/home' component={HomePage} />
								<Route path='/results' component={ResultsHome}/>
								<Route path='/logout' component={HomePage} />
								<Route path='/updatestudentprofile' component={UpdateStudentProfile} />
								<Route path='/stuhome' component={StudentHome} />
								<Route path='/examuimain' component={Main2} />
								<Route path = '/blueprint' component = {BluePrint} />
								<Route path='/startexam' component={StartExam} />
								<Route path='/addquestions' component={App} />
      					<Route path = "/questionDetails" component={QuestionLayout} />
								<Route path = "/searchq" component={SearchQ} />
								<Route path="/stulogin" component={StudentLogin} />
								<Route path="/backtoexamhome" component={SearchQ} />
								<Route path='*' component={ComponentNotFound} />
        </Route>
				</Router> , document.getElementById('root')
);
