import React from 'react';
import ReactDom from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Main from './container/Main';
import Nav from './container/Nav';
import ComponentNotFound from './container/ComponentNotFound';
import Register from './container/Register';
import Login from './container/Login';
import StudentHome from './container/StudentHome';
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
								<Route path="/login" component={Login}/>
								<Route path="/loginMain" component={StudentHome}/>
                <Route path='*' component={ComponentNotFound} />
        </Route>
				</Router> , document.getElementById('root')
);
