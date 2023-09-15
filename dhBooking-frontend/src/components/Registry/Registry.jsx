import React, { useState, useContext } from "react";
import {Data} from "../../Data/Users.js";
import eye from "../../Assets/Images/eye.svg";
import { Navigate, Link } from "react-router-dom";
import { DataContext } from "../../Context/DataProvider.js"
import {Container, Sesion, ButtonSubmit, Card, User, Boxes, BoxesWrong, BoxesNames, BoxesWrongNames, Registry, Error2, Names, Div, Eye} from "./registryStyle.js";

const RegistryApp = () =>{
    const [info, setInfo] = useState(
        {
            name: "",
            lastname: "",
            email: "",
            pass: "",
            passRepeat: ""
        }
    )
    const [state, dispatch] = useContext(DataContext)
    const [error, setError] = useState(false)
    const [errors, setErrors] = useState({})
    const [verify, setVerify] = useState(false)
    const [created, setCreated] = useState(false)
    const [passwordType, setPasswordType] = useState("password");

    const validation = () => {
        let errors = {};
        let isValid = true;
        if (verify){
                setVerify(false);
                if(!info.name){
                    isValid = false;
                    errors.name = "Este campo es obligatorio";
                 }
                 if(!info.lastname){
                    isValid = false;
                    errors.lastname = "Este campo es obligatorio";
                 }
                if(!info.email){
                    isValid = false;
                    errors.email = "Este campo es obligatorio";
                }
                if(info.email === Data.email){
                    isValid = false;
                    errors.email = "Ese email ya existe";
                }
                if(info.email !== "undefined" && !info.email === false){
                    let at = info.email.lastIndexOf('@');
                    let dot = info.email.lastIndexOf('.');
          
                    if (!(at < dot && at > 0 && info.email.indexOf('@@') === -1 && dot > 2 && (info.email.length - dot) > 2)) {
                       isValid = false;
                       errors.email = "Email inválido";
                     }
                }
                if(!info.pass){
                    isValid = false;
                    errors.pass = "Este campo es obligatorio";
                }
                if (info.pass.length < 6){
                    isValid = false;
                    errors.pass = "Debe tener mas de 6 carácteres";;
                    }
                if(!info.passRepeat){
                    isValid = false;
                    errors.passRepeat = "Este campo es obligatorio";
                }
                if(info.pass !== info.passRepeat){
                    isValid = false;
                    errors.passRepeat = "Las contraseñas deben ser iguales";
                }
        setErrors(errors);
        return isValid;
    }
}

const togglePassword =()=>{
    if(passwordType==="password"){
    setPasswordType("text")
    return;
    }
    setPasswordType("password")
    }

const handleChange = (e) =>{
    setInfo(
    {...info,
    [e.target.name]: e.target.value
    });
}

const handleSubmit = async (e) => {
    e.preventDefault();
    if (validation()) {
      try {
        const requestOptions = {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            name: info.name,
            lastname: info.lastname,
            email: info.email,
            password: info.pass,
            role: {
              id: 2,
            },
          }),
        };
  
        const response = await fetch('http://localhost:8081/users', requestOptions);
        if (response.status === 400) {
          errors.email = "Ese email ya existe";
          return setErrors(errors);
        }
        if (response.status !== 400) {
          setError(false);
        }
        const data = await response.json();
        sessionStorage.setItem("user", data.jwt);
        sessionStorage.setItem("dataUser", JSON.stringify(data));
        // check if the "role" property exists before accessing it
        if (data && data.role) {
          // access the "role" property
          console.log(data.role);
        }
        setCreated(true);
      } catch (error) {
        // handle error
      }
    }
  }
  
  
    


return(
        <Container>
            <Sesion>Crear Cuenta</Sesion>
                <form onSubmit={handleSubmit}>
                <Card>
                    <Names>
                    <span>
                        <User>Nombre</User>
                        {!errors.name ?
                        <BoxesNames onChange={handleChange} value={info.name}
                        type="text" name="name"
                        /> 
                        : 
                        <span>
                        <BoxesWrongNames  onChange={handleChange} value={info.name}
                        type="text" name="name" />
                        <Error2>{errors.name}</Error2>
                        </span>
                        }
                    </span>
                    <span>
                        <User>Apellido</User>
                        {!errors.lastname ?
                        <BoxesNames onChange={handleChange} value={info.lastname}
                        type="text" name="lastname"
                        /> 
                        : 
                        <span>
                        <BoxesWrongNames onChange={handleChange} value={info.lastname}
                        type = "text" name="lastname"/>
                        <Error2>{errors.lastname}</Error2>
                        </span>
                        }
                    </span>
                    </Names>
                    <span>
                        <User>Correo Electronico</User>
                        {!errors.email ?
                        <Boxes onChange={handleChange} value={info.email}
                        type="text" name="email"
                        /> 
                        : 
                        <span>
                        <BoxesWrong onChange={handleChange} value={info.email}
                        type = "text" name="email"/>
                        <Error2>{errors.email}</Error2>
                        </span>
                        }
                    </span>
                    <span>
                        <User>Contraseña</User>
                        {!errors.pass ?
                        <Div>
                            <Boxes onChange={handleChange} value={info.pass}
                            type={passwordType} name="pass"
                            />
                            <Eye onClick={togglePassword} src={eye}></Eye>
                        </Div>
                        :
                        <Div>
                            <BoxesWrong onChange={handleChange} value={info.pass}
                            type={passwordType} name="pass"/>
                            <Eye onClick={togglePassword} src={eye}></Eye>
                            <Error2>{errors.pass}</Error2>
                        </Div>
                        
                        }
                    </span>
                    <span>
                        <User>Confirmar Contraseña</User>
                        {!errors.passRepeat ? 
                        <Boxes onChange={handleChange} value={info.passRepeat}
                        type="password" name="passRepeat"/>
                        : 
                        <span>
                        <BoxesWrong onChange={handleChange} value={info.passRepeat}
                        type="password" name="passRepeat"/>
                        <Error2>{errors.passRepeat}</Error2>
                        </span> 
                        } 
                    </span>
                    <span>
                        <ButtonSubmit onClick={ ()=> setVerify(true)}
                        type = "submit">Crear cuenta</ButtonSubmit>
                    </span>
                    <span>
                        <Registry>¿Ya tienes una cuenta?</Registry>
                        <Registry><Link to= "/login" >Iniciar sesión</Link></Registry>
                    </span>
                </Card>
                </form>
        {created ? <Navigate to="/login"/> : null}
        </Container>
        )
                    }

export default RegistryApp;