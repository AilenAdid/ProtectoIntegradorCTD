import { useState, useContext, useEffect } from "react";
import { DataContext } from "../../Context/DataProvider";
import { types } from "../../Context/dataReducer";
import { PolicyWrapper} from "./Policy.styles"
import { LabelTextArea } from "../Administration/AdministrationStyle";

const Policy = () => {

  const [state, dispatch] = useContext(DataContext)
  const {newProductData} = state

  const [policies, setPolicies] = useState({
    rules: '',
    securityAndHealth: '',
    cancellation: ''
  })

  const policiesHandler = (e)=>{
    setPolicies(
      {...policies,
      [e.target.name]: e.target.value
    });
  }

  
  useEffect(()=>{
    dispatch({
      type: types.addPolicies,
      payload: policies
  })
  },[dispatch,policies])

  return (
    <PolicyWrapper>
      <div>
        <h4>Reglas</h4>
        <LabelTextArea>
          <textarea 
            value={policies.rules}
            name="rules"
            //rows={10}
            onChange={(e)=>policiesHandler(e)}
            placeholder="Ejemplo:
            Edad mínima para hacer el check-in: 18
            Los huéspedes menores de edad deben ir acompañados de un adulto
            Se aceptan perros y gatos con un peso máximo de 25 kg"
          />
        </LabelTextArea>
      </div>
      <div>
        <h4>Salud y seguridad</h4>
        <LabelTextArea>
          <textarea
           value={policies.securityAndHealth}
           name="securityAndHealth"
           //rows={10}   
           onChange={(e)=>policiesHandler(e)}  
           placeholder="Ejemplo:
           Tapabocas para huéspedes
           Indumentaria segura descartable para huéspedes
           Desinfección de áreas comunes"
          />          
        </LabelTextArea>
      </div>
      <div>
        <h4>Política de cancelación</h4>
        <LabelTextArea>
          <textarea
           value={policies.cancellation}
           name="cancellation"
           //rows={10}  
           onChange={(e)=>policiesHandler(e)}
           placeholder="Ejemplo:
           Cancelación gratis hasta 48 hs antes de la reserva."
          />
        </LabelTextArea>
      </div>
    </PolicyWrapper>
  );
};

export default Policy;
