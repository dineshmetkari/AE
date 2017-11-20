import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import axios from 'axios';
import request from 'superagent';
import {Link} from 'react-router';

const style = {
 margin: 15,
marginLeft: 600
};
export default class  Register extends React.Component {
 constructor(props) {
   super(props);
   this.onSubmit=this.handleSubmit.bind(this);

   this.state = {
           fields: {},
           errors: {},

       }
}



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
             if(!fields["sname"].match(/^[a-zA-Z]+$/)){
                 formIsValid = false;
                 errors["sname"] = "Only letters";
             }
        }

        //Email
        if(!fields["emailId"]){
           formIsValid = false;
           errors["emailId"] = "Cannot be empty";
        }

        if(typeof fields["emailId"] !== "undefined"){
            let lastAtPos = fields["emailId"].lastIndexOf('@');
            let lastDotPos = fields["emailId"].lastIndexOf('.');

            if (!(lastAtPos < lastDotPos && lastAtPos > 0 && fields["emailId"].indexOf('@@') == -1 && lastDotPos > 2 && (fields["emailId"].length - lastDotPos) > 2)) {
              formIsValid = false;
              errors["emailId"] = "Email is not valid";
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

        this.setState({errors: errors});
       return formIsValid;
}


handleSubmit(e) {
   e.preventDefault();

   if(this.handleValidation()){
           alert("Form submitted");
        }else{
           alert("Form has errors.")
        }
   var self = this;

   const payload = {

   studentName: this.refs.sname.getValue(),
   emailId: this.refs.emailId.getValue(),
   contactNumber: this.refs.contact.getValue(),
   skillSet: this.refs.skill.getValue(),
   address: this.refs.address.getValue(),
   password:this.refs.password.getValue(),
   confirmPassword:this.refs.confirmpassword.getValue()
};
console.log(payload);
request
.post('http://localhost:8083/students')
.set('Content-type', 'application/json')
.send(payload)
.end((res, err) =>{
  if(res) {
    console.log("this is res", res.body);
  } else {
    console.log("this is err", err);
  }
})
 }

 handleChange(field, e){
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

handleChangeConfirm = (e) =>{
  console.log("confirm password", e.target.value);
  this.setState({recheck: e.target.value}, () => {
    this.state.password;
    this.state.recheck;
  });
}

render() {
   return (
     <form>
     <div style={style}>
     <TextField ref='sname' hintText="Enter Student Name" floatingLabelText="StudentName" onChange={this.handleChange.bind(this, "sname")} value={this.state.fields["sname"]}/>
     <span style={{color: "red"}}>{this.state.errors["sname"]}</span>
     <br/>
     <TextField ref='emailId' hintText="Enter your Email id" floatingLabelText="Email Id" onChange={this.handleChange.bind(this, "emailId")} value={this.state.fields["emailId"]}/>
     <span style={{color: "red"}}>{this.state.errors["emailId"]}</span>
     <br/>
     <TextField ref='contact' hintText="Enter your contact number" floatingLabelText="Contact Number" onChange={this.handleChange.bind(this, "contact")} value={this.state.fields["contact"]}/>
     <span style={{color: "red"}}>{this.state.errors["contact"]}</span>
     <br/>
     <TextField ref='skill' hintText="Enter your skills ex:java,c" floatingLabelText="Skill set" onChange={this.handleChange.bind(this, "skill")} value={this.state.fields["skill"]}/>
     <span style={{color: "red"}}>{this.state.errors["skill"]}</span>
     <br/>
     <TextField ref='address' hintText="Enter your address ex:Bangalore,hyd" floatingLabelText="Address" onChange={this.handleChange.bind(this, "address")} value={this.state.fields["address"]}/>
     <span style={{color: "red"}}>{this.state.errors["address"]}</span>
     <br/>
     <TextField ref='password' hintText="Enter your password" type="password" floatingLabelText="Password" />
     <br/>
     <TextField ref='confirmPassword' hintText="Enter your confirmpassword" type="password" floatingLabelText="Confirm Password" onChange={this.handleChangeConfirm.bind(this)}/>
     <br/>
     <br/>

     <RaisedButton label="Register" primary={true} onClick={this.onSubmit}/>

     <h4>Already Registered, login <Link to="/login">Here</Link></h4>
     <br/>



     </div>
         </form>


   );
 }


}
