/* tslint:disable */
/* eslint-disable */
/**
 * The Automation Configuration API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface WorkflowInputModel
 */
export interface WorkflowInputModel {
    /**
     * The descriptive name of an input
     * @type {string}
     * @memberof WorkflowInputModel
     */
    label?: string;
    /**
     * The name of an input
     * @type {string}
     * @memberof WorkflowInputModel
     */
    name: string;
    /**
     * If an input is required, or not
     * @type {boolean}
     * @memberof WorkflowInputModel
     */
    required?: boolean;
    /**
     * The type of an input, for example \"string\"
     * @type {string}
     * @memberof WorkflowInputModel
     */
    type?: string;
}

/**
 * Check if a given object implements the WorkflowInputModel interface.
 */
export function instanceOfWorkflowInputModel(value: object): boolean {
    if (!('name' in value)) return false;
    return true;
}

export function WorkflowInputModelFromJSON(json: any): WorkflowInputModel {
    return WorkflowInputModelFromJSONTyped(json, false);
}

export function WorkflowInputModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): WorkflowInputModel {
    if (json == null) {
        return json;
    }
    return {
        
        'label': json['label'] == null ? undefined : json['label'],
        'name': json['name'],
        'required': json['required'] == null ? undefined : json['required'],
        'type': json['type'] == null ? undefined : json['type'],
    };
}

export function WorkflowInputModelToJSON(value?: WorkflowInputModel | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'label': value['label'],
        'name': value['name'],
        'required': value['required'],
        'type': value['type'],
    };
}
