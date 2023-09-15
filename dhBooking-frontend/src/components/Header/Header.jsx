import React,{createContext, useContext, useState} from "react";
import logoMobile from "../../Assets/Images/logoDB_mobile.svg";
import logoDesktop from "../../Assets/Images/logoDB_desktop.svg";
import {
  HeaderDB,
  MobileLogo,
  DesktopLogo,
  Button,
  ButtonContanier,
  LogoContainer,
  Administration,
} from "./Header.styles";
import MobileMenu from "../MobileMenu/MobileMenu";
import { useNavigate } from "react-router-dom";
import { User, Card, Circle } from "./headerStyle.js";
import { DataContext } from "../../Context/DataProvider";
import { types } from "../../Context/dataReducer";
import { EndpointContext } from "../../Layout/MainLayout";

const Header = () => {
  const navigate = useNavigate();
  const pathName = window.location.pathname;

  const clear = () => {
    sessionStorage.clear();
    window.location = "/";
  };

  const [state ,dispatch] = useContext(DataContext)
  const [endpoint, setEndpoint] = useContext(EndpointContext);
  const {userData} = state

  const dataProductHandler=()=>{
    dispatch({
      type: types.allProducts
    })
    sessionStorage.removeItem("checkIn")
    sessionStorage.removeItem("checkOut")
  }

  return (
    <>
        <HeaderDB>
          <MobileMenu />
          <LogoContainer>
            <MobileLogo
              src={logoMobile}
              alt="Logo Digital Booking"
              onClick={() => {setEndpoint('http://localhost:8081/products');navigate("/"); dataProductHandler()}}
              ></MobileLogo>
            <DesktopLogo
              src={logoDesktop}
              alt="Logo Digital Booking"
              onClick={() => {setEndpoint('http://localhost:8081/products');navigate("/");dataProductHandler()}}
              ></DesktopLogo>
          </LogoContainer>
          {userData ? (
          
            <Card>
              {
              userData.role?.id === 1? <Administration onClick={() => {navigate("/Administration")}}>Administración</Administration> : ""}
              
            {/*<Circle>
              {userData?.name.charAt(0).toUpperCase()}
              {userData?.surname.charAt(0).toUpperCase()}
               
          </Circle>*/}
          
            <User>
                <h4>Hola, </h4>
                <p>
                  {userData?.name} {userData?.surname}
                </p>
            </User>
              <button onClick={clear}>X</button>
            </Card>
          
              ) : (
            <ButtonContanier>
              {pathName!== "/register" && <Button onClick={() => navigate("/register")}>Crear cuenta </Button>}
              {pathName!== "/login" &&<Button onClick={() => navigate("/login")}>Iniciar sesión</Button>}
            </ButtonContanier> )}
        </HeaderDB>
    </>
  );
};

export default Header;
