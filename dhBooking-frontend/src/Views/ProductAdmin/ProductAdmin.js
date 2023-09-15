import { Main } from "./ProductAdmin.styles"
import { Header } from "../../components/SpecificProduct/SpecificProduct"
import FeatureInput from "../../components/ProductAdmin/FeatureInput"

const ProductAdmin =()=>{
    return(
        <Main>
            <Header
                isAdminHeader={true}
                title={"Administración"}
            />
            <h2>Crear propiedad</h2>
            <FeatureInput/>
        </Main>
    )
}

export default ProductAdmin