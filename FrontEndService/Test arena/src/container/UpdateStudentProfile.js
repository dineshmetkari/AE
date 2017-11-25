import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import axios from 'axios';
import Snackbar from 'material-ui/Snackbar';
import request from 'superagent';
import {browserHistory} from 'react';
import {Link} from 'react-router';

const style = {
 margin: 15,
marginLeft: 600
};
let email=[];
export default class  UpdateStudentProfile extends React.Component {
 constructor(props) {
   super(props);
   this.onSubmit=this.handleSubmit.bind(this);
   this.handleTouchTap=this.handleTouchTap.bind(this);
   this.state = {
           fields: {},
           errors: {},
           open: false,
           password: "",
           recheck: "",
           result: [],


       }
}


handleTouchTap = () => {
    this.setState({
      open: true,
    });
  };
  handleRequestClose = () => {
    this.setState({
      open: false,
    });
  };



handleValidation(){
      let fields = this.state.fields;
        let errors = {};
        let formIsValid = true;

        //studentName
        if(!fields["sname"]){
           formIsValid = false;
           errors["sname"] = "Can not be empty";
        }
        if(typeof fields["sname"] !== "undefined"){
             if(!fields["sname"].match(/^[a-zA-Z]+(?:[ ]?[a-zA-Z])*$/)){
                 formIsValid = false;
                 errors["sname"] = "Only letters";
             }
        }


       //contactNumber
       if(!fields["contact"]){
          formIsValid = false;
          errors["contact"] = "Can not be empty";
       }
       if(typeof fields["contact"] !== "undefined"){
            if(!fields["contact"].match(/^[0-9\+]{9,15}$/)){
                formIsValid = false;
                errors["contact"] = "Invalid contact";
            }
       }

        //skillSet
        if(!fields["skill"]){
           formIsValid = false;
           errors["skill"] = "Can not be empty";
        }
        if(typeof fields["skill"] !== "undefined"){
             if(!fields["skill"].match(/^[a-zA-Z]+$/)){
                 formIsValid = false;
                 errors["skill"] = "Only letters";
             }
        }

        //Address
        if(!fields["address"]){
           formIsValid = false;
           errors["address"] = "Can not be empty";
        }
        if(typeof fields["address"] !== "undefined"){
             if(!fields["address"].match(/^[a-zA-Z]+$/)){
                 formIsValid = false;
                 errors["address"] = "Invalid Address";
             }
        }

        //Password
        if(!fields["password"]){
           formIsValid = false;
           errors["password"] = "Can not be empty";
        }
        if(typeof fields["password"] !== "undefined"){
             if(!fields["password"].match(/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/)){
                 formIsValid = false;
                 errors["password"] = "at least one number, one lowercase,one uppercase letter and atleast 6 characters";
             }
        }


        //confirmPassword
        if(!fields["confirmPassword"]){
           formIsValid = false;
           errors["confirmPassword"] = "Can not be empty";
        }
        if(typeof fields["confirmPassword"] !== "undefined"){
            //  if(!fields["password"]==fields["confirmPassword"]){
            //      formIsValid = false;
            //      errors["confirmPassword"] = "Password and confirmPassword must be match";
            //  }
               if(!(this.refs.password.getValue()==this.refs.confirmPassword.getValue())){
                     formIsValid = false;
                     errors["confirmPassword"] = "Password and confirmPassword must be match";
               }
         }

        this.setState({errors: errors});
       return formIsValid;
}


handleSubmit(e) {
  var t=this;
   e.preventDefault();
  //  const emailId=this.refs.emailId.getValue();
  var user = this.props.location.state;
  console.log("here is updated frofile" + user);
   var payload = {

      studentName: this.refs.sname.getValue(),
       emailId: user,
       contactNumber: this.refs.contact.getValue(),
       skillSet: this.refs.skill.getValue(),
       address: this.refs.address.getValue(),
       password:this.refs.password.getValue()
};
console.log(payload);

      if(this.handleValidation()){

                              request
                              .post('http://172.23.239.157:8083/students')
                              .set('Content-type', 'application/json')
                              .send(payload)
                              .end((res, err) =>{
                                if(res) {
                                  console.log("this is res", res.body);
                                } else {
                                  console.log("this is err", err);
                                }
                              })
                              //  alert("Profile Updated successfully");
                               //this.setState({open:true});
                               {this.handleTouchTap()}
                              //browserHistory.push('/error');



                              return;




                  }else{
                        // alert("Registration not done!! give proper data")
                    }
            }

 handleChange(field, e){
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }


render() {

   return (

     <form>
     <div style={style}>
     <TextField ref='sname' hintText="Enter StudentName" floatingLabelText="StudentName" onChange={this.handleChange.bind(this, "sname")} value={this.state.fields["sname"]}/>
     <span style={{color: "red"}}>{this.state.errors["sname"]}</span>
     <br/>
     <TextField ref='password' hintText="Enter your Password" type="password" floatingLabelText="Password" onChange={this.handleChange.bind(this, "password")} value={this.state.fields["password"]}/>
     <span style={{color: "red"}}>{this.state.errors["password"]}</span>
     <br/>
      <TextField ref='confirmPassword' hintText="Enter your Confirm Password" type="password" floatingLabelText="Confirm Password" onChange={this.handleChange.bind(this, "confirmPassword")} value={this.state.fields["confirmPassword"]}/>
     <span style={{color: "red"}}>{this.state.errors["confirmPassword"]}</span>
     <br/>
     <TextField ref='contact' hintText="Enter your Contact Number" floatingLabelText="Contact Number" onChange={this.handleChange.bind(this, "contact")} value={this.state.fields["contact"]}/>
     <span style={{color: "red"}}>{this.state.errors["contact"]}</span>
     <br/>
     <TextField ref='skill' hintText="Enter your Skill Set ex:java,c" floatingLabelText="Skill Set" onChange={this.handleChange.bind(this, "skill")} value={this.state.fields["skill"]}/>
     <span style={{color: "red"}}>{this.state.errors["skill"]}</span>
     <br/>
     <TextField ref='address' hintText="Enter your City ex:Bangalore" floatingLabelText="City" onChange={this.handleChange.bind(this, "address")} value={this.state.fields["address"]}/>
     <span style={{color: "red"}}>{this.state.errors["address"]}</span>
     <br/>
     <br/>

     <RaisedButton label="Update" primary={true} onClick={this.onSubmit}/>

     <RaisedButton label="Back" primary={true} containerElement={<Link to="/stuhome"/>} style={{margin:'10px'}}/>
     <br/>
     <Snackbar
          open={this.state.open}
          message="Profile updated successfully"
          autoHideDuration={4000}
          onRequestClose={this.handleRequestClose}
        />


     </div>
         </form>


   );
 }


}
