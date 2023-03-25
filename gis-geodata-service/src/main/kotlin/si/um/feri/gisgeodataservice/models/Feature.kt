package si.um.feri.gisgeodataservice.models

import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class Feature(var type: String, var properties: HashMap<String, Any>, var geometry: Geometry) {
}