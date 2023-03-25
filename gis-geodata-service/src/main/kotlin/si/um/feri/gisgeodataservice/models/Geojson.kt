package si.um.feri.gisgeodataservice.models

import org.springframework.data.mongodb.core.mapping.Document
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Document()
data class Geojson(var id: String, var type: String, var name: String, var crs: HashMap<String, Any>, var features: List<Feature>) {
}