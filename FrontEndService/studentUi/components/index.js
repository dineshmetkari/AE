import React from 'react';
import ReactDom from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Main from './container/Main';
import Nav from './container/Nav';
import ComponentNotFound from './container/ComponentNotFound';
import Register from './container/Register';
import StudentLogin from './container/StudentLogin';
import StudentHome from './container/StudentHome';
import ResultsHome from './container/ResultsHome';
import AdminLogin from './container/AdminLogin';
import HomePage from './container/HomePage';
import AdminHome from './container/AdminHome';
import {browserHistory, Route, Router, IndexRoute}  from 'react-router';


class Index extends React.Component{
	render(){
		return(

			<MuiThemeProvider>
			<div>
					<Nav />
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
								<Route path='results/:name' component={ResultsHome}/>
								<Route path='/logout' component={HomePage} />
								<Route path='*' component={ComponentNotFound} />
        </Route>
				</Router> , document.getElementById('root')
);
