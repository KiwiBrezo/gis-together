package si.um.feri.gisgeodataservice.models

import org.springframework.data.mongodb.core.mapping.Document
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Document
class FeatureCollection(type: String, name: String, crs: HashMap<String, JvmType.Object>, features: List<Feature>) {
}