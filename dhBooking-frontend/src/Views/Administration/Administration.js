import React, { useContext } from "react";
//import AdministrationForm from "../../Components/Administration/Administration.jsx";
import { Header } from "../../components/SpecificProduct/SpecificProduct";
import { useNavigate } from "react-router-dom"; 
import { Main } from "./ProductAdmin.styles.js"
import AdministrationForm from "../../components/ProductAdmin/AdministrationForm.jsx";
import { DataContext } from "../../Context/DataProvider";

const Administration =()=>{
    const navigate = useNavigate()
    const [state] = useContext(DataContext)
    const {userData} = state

    const verifyAccess = ()=>{
        if(userData === null){
            navigate("/home")
        }
        if(userData !== null) {
            if(userData.role.name !=="ROLE_ADMIN"){
                navigate("/home")
            }
        }
        return
    }
   
    verifyAccess()

    return(
        <Main>
            <Header
                isAdminHeader={true}
                title={"Administración"}
            />
            <h2>Crear propiedad</h2>
            <AdministrationForm/>
        </Main>
    )
}

export default Administration;